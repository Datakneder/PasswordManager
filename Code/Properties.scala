package nl.datakneder.temp
    {
        import scala.language.reflectiveCalls
        import scala.language.implicitConversions
        import nl.datakneder.core.Utils.Framework._
        import nl.datakneder.temp.Calculation._
        import nl.datakneder.core.Packages._
        
        object Properties
            {
                object Variables
                    {
                        object Variable
                            extends Template_Factory("Variable")
                                {
                                    trait Interface[A,B]
                                        {
                                            def calculation() : iHasCalculation[A]
                                            def apply() : A
                                            def assign(_x : A) : B 
                                            def apply(_x : Long => A, _d : iHasCalculation[_]*) : B
                                        }
                                    class Class[A, B](_n : Long => A, _owner : B)
                                        extends Interface[A, B]
                                            {
                                                private var __value = new Calculation[A](_n)
                                                def calculation() : iHasCalculation[A] = __value
                                                def apply() : A = __value()
                                                def assign(_x : A) : B = apply({_ => _x})
                                                def apply(_x : Long => A, _d : iHasCalculation[_]*) : B = Reflect(__value(_x, _d :_*), _owner)
                                            }
                                    trait iFactory
                                        {
                                            def apply[A,B](_n : Long => A, _owner : B) : Interface[A,B]
                                        }
                                    factory(
                                        new iFactory
                                            {
                                                def apply[A,B](_n : Long => A, _owner : B) : Interface[A,B] = new Class[A,B](_n, _owner)
                                            })
                                    def apply[A,B](_n : Long => A, _owner : B) : Interface[A,B] = factory()(_n, _owner)
                                }
                        implicit def InjectOptionalVariable[A, B](_x : OptionalVariable.Interface[A, B]) =
                            new Object
                                {
                                    def assign[C](_y : C) : OptionalVariable.Interface[A, B] = Reflect(Try(_x.assign(Some(_y.asInstanceOf[A]))), _x)
                                }
                        object OptionalVariable
                            extends Template_Factory("OptionalVariable")
                                {
                                    trait Interface[A, B]
                                        extends Variable.Interface[Option[A], B]
                                            {
                                            }
                                    class Class[A, B](_owner : B)
                                        extends Variable.Class[Option[A], B]({_ => None}, _owner)
                                        with Interface[A, B]
                                            {
                                            }
                                    trait iFactory
                                        {
                                            def apply[A,B](_owner : B) : Interface[A,B]
                                        }
                                    factory(
                                        new iFactory
                                            {
                                                def apply[A,B](_owner : B) : Interface[A,B] = new Class[A,B](_owner)
                                            })
                                    def apply[A,B](_owner : B) : Interface[A,B] = factory()(_owner)
                                }
                        object ListVariable
                            extends Template_Factory("ListVariable")
                                {
                                    trait Interface[A, B]
                                        extends Variable.Interface[List[A], B]
                                            {
                                                def clear() : B
                                                def size() : Int
                                                def add(_x : A*) : B
                                                def add(_x : ListVariable.Interface[A, _]) : B
                                                def remove(_x : A*) : B
                                                def remove(_x : ListVariable.Interface[A, _]) : B
                                            }
                                    class Class[A, B](_owner : B)
                                        extends Variable.Class[List[A], B]({_ => List()}, _owner)
                                        with ListVariable.Interface[A, B]
                                            {
                                                def clear() : B = assign(List[A]())
                                                def size() : Int = apply().size
                                                def add(_x : A*) : B = assign(apply() ++ _x.toList)
                                                def add(_x : ListVariable.Interface[A, _]) : B = add(_x() :_*)
                                                def remove(_x : A*) : B = assign(apply().filter(!_x.contains(_)))
                                                def remove(_x : ListVariable.Interface[A, _]) : B = remove(_x() :_*)
                                            }
                                    trait iFactory
                                        {
                                            def apply[A,B](_owner : B) : Interface[A,B]
                                        }
                                    factory(
                                        new iFactory
                                            {
                                                def apply[A,B](_owner : B) : Interface[A,B] = new Class[A,B](_owner)
                                            })
                                    def apply[A,B](_owner : B) : Interface[A,B] = factory()(_owner)
                                }
                    }
                    import Variables._
                object Property
                    extends Template_Factory("Property")
                        {
                            trait Interface
                                {__property =>
                                    val caption = Variable({_ => ""}, this)
                                    val parent = OptionalVariable[Property.Interface, this.type](this)
                                    private val __parent = new Calculation[Option[Property.Interface]]({_ => None})
                                    def parents() : List[Property.Interface] = parents(true)
                                    def parents(_x : Boolean) : List[Property.Interface] = 
                                        {
                                            var result : List [Property.Interface] = if (_x) List(this) else List()
                                            result ++ parent().map(_.parents(true)).getOrElse(List())
                                        }
                                    val children = ListVariable[Property.Interface, this.type](this)
                                }
                        }
                object PropertyValue
                    extends Template_Factory("PropertyValue")
                        {
                            trait Interface[A]
                                extends Property.Interface
                                with iHasCalculation[A]
                                    {
                                        private val __inputString = new Calculation[Option[String]]({_ => None})
                                        private val __inputValue = new Calculation[Option[A]]({_ => None})
                                        private val __useString = new Calculation[Boolean]({_ => true})
                                        private val __read = new Calculation[String => Option[A]]({_ => {_ => None}})
                                        private val __write = new Calculation[A => String]({_ => {_.toString}})
                                        private var __default = new Calculation[Option[A]]({_ => None})
                                        
                                        private val __value =
                                            new Calculation[Option[A]](
                                                {n => 
                                                    if (__useString(n)) 
                                                        TryCatch({__read(n)(__inputString(n).get)}, None) 
                                                    else 
                                                        __inputValue(n)
                                                }).dependencies(__inputString, __inputValue, __useString, __read)
                                        private val __outputString = 
                                            new Calculation[Option[String]](
                                                {n => 
                                                    if (__useString(n)) 
                                                        __inputString() 
                                                    else 
                                                        TryCatch(Some(__write()(__value().get)), None) 
                                                }).dependencies(__inputString, __value, __useString, __write)
                                        private val __outputValue = new Calculation[A]({n => __value().getOrElse(__default().get)}).dependencies(__value, __default)
                                        val Calculation = __outputValue
                                        
                                        def stringValue() : Option[String] = __outputString()
                                        def stringValue(_x : Option[String]) : this.type = 
                                            Reflect(
                                                    {
                                                        __inputString({_ => _x})
                                                        __useString({_ => true})
                                                    }, this)
                                        def stringValue(_x : String) : this.type = stringValue(Some(_x))
                                        def value() : Option[A] = __value()
                                        def value(_n : Long) : Option[A] = __value(_n)
                                        def apply(_x : A) : this.type = 
                                            Reflect(
                                                {
                                                    __inputValue({_ => Some(_x)})
                                                    __useString({_ => false})
                                                }, this)
                                        
                                        def read(_x : String => Option[A]) : this.type = Reflect(__read({_ => _x}), this)
                                        def write(_f : A => String) : this.type = Reflect(__write({_ => _f}), this)
                                        def default() : Option[A] = __default()
                                        def apply() : A = __outputValue() 
                                        def apply(_n : Long) : A = __outputValue(_n) 
                                        def apply(_f : Long => Option[A]) : this.type = 
                                            Reflect(
                                                {
                                                    __inputValue(_f)
                                                    __useString({_ => false})
                                                }, this)
                                        def dependencies(_x : iHasCalculation[_]*) : this.type = Reflect({__inputValue.dependencies(_x :_*)}, this)
                                        def default(_x : A) : this.type = Reflect(__default({_ => Some(_x)}), this)
                                        def isCalculated() : Boolean = !__useString() && __inputValue.dependencies().size > 0
                                    }
                            
                            
                        }
                object PropertyCollection
                    extends Template_Factory("PropertyCollection")
                        {
                            trait Interface[A <: Property.Interface]
                                extends Property.Interface
                                with iHasCalculation[List[A]]
                                    {
                                        private val __value =  new Calculation[List[A]]({_ => List[A]()})
                                        private val __display = new Calculation[A => String]({_ => {x => x.toString}})
                                        private val __stringList = new Calculation[List[String]]({_ => __value().map(__display()(_))}).dependencies(__value, __display)
                                        val Calculation = __value
                                        
                                        def apply() : List[A] = __value()
                                        def apply(_n : Long) : List[A] = __value(_n)
                                        def apply(_f : Long => List[A]) : this.type = Reflect({__value(_f)}, this)
                                        def dependencies(_x : iHasCalculation[_]*) : this.type = Reflect({__value.dependencies(_x :_*)}, this) 
                                        def add(_x : Any) : this.type = 
                                            Reflect(
                                                {
                                                    Try(
                                                        {
                                                            if (_x != null) 
                                                                {
                                                                    val l = __value() :+ _x.asInstanceOf[A]
                                                                    __value({_ => l})
                                                                }
                                                        })
                                                }, this)
                                        def remove(_x : A) : this.type = 
                                            Reflect(
                                                {
                                                    val l = __value().filter(_ != _x)
                                                    __value({_ => l})
                                                }, this)
                                        def clear() : this.type = Reflect(__value({_ => List[A]()}), this)
                                        def size() : Int = __value().size
                                        val construction = ListVariable[Constructor[_], this.type](this)
                                        def display(_x : A => String) : this.type = Reflect(__display({_ => _x}), this)
                                        def stringList() : List[String] = __stringList()
                                        def selection() : PropertySelection.Interface[A] = 
                                            {
                                                val result = new PropertySelection.Interface[A] {}
                                                result.collection.assign(this)
                                                result
                                            }
                                        def swap(_i : Int, _j : Int) : this.type = 
                                            Reflect(
                                                {
                                                    val l = Lists.swap(_i, _j, __value())
                                                    __value({_ => l})
                                                }, this)
                                    }
                        }
                object PropertySelection
                    extends Template_Factory("PropertyCollection")
                        {
                            trait Interface[A <: Property.Interface]
                                extends Property.Interface
                                    {
                                        private val __value = ListVariable[String, this.type](this)
                                        def apply() : List[String] = __value()
                                        def apply(_x : List[String]) : this.type = Reflect(__value.assign(_x), this)
                                        def add(_x : String) : this.type = 
                                            Reflect(
                                                {
                                                    Try(if (_x != null) __value.add(_x))
                                                }, this)
                                        def remove(_x : String) : this.type = __value.remove(_x)
                                        def clear() : this.type = __value.clear()
                                        def size() : Int = __value.size
                                        val singleSelection = Variable({_ => false}, this)
                                        def moveUp() : this.type =
                                            Reflect(
                                                {
                                                    collection() match 
                                                        {
                                                            case None => 
                                                            case Some(collection) =>
                                                                selectedItems()
                                                                    .take(1)
                                                                    .foreach(
                                                                        {p =>
                                                                            val i = collection().indexOf(p)
                                                                            if (i > 0) collection.swap(i, i-1)
                                                                        })
                                                        }
                                                }, this)
                                        def moveDown() : this.type =
                                            Reflect(
                                                {
                                                    collection() match 
                                                        {
                                                            case None => 
                                                            case Some(collection) =>
                                                                selectedItems()
                                                                    .take(1)
                                                                    .foreach(
                                                                        {p =>
                                                                            val i = collection().indexOf(p)
                                                                            if (i < collection().size - 1) collection.swap(i, i+1)
                                                                        })
                                                        }
                                                }, this)
                                        var collection = OptionalVariable[PropertyCollection.Interface[A], this.type](this)
                                        def selectedItems() : List[A] = 
                                            {
                                                collection() match 
                                                    {
                                                        case None => 
                                                            List[A]()
                                                        case Some(collection) =>
                                                            val l = collection.stringList().map(_.trim)
                                                            __value()
                                                                .map(l.indexOf(_))
                                                                .filter(_ >= 0)
                                                                .map(collection()(_))
                                                    }
                                            }
                                        def removeFromCollection() : this.type = 
                                            Reflect(
                                                {
                                                    selectedItems().foreach({i => collection().get.remove(i)})
                                                }, this)
                                    }
                        }
                case class Constructor[A](name : String, construct : () => A)
                class TupleTemplate(_x : String)
                    extends Property.Interface
                        {__template =>
                            caption({_ => _x})
                            def add[A <: Property.Interface](_x : A) : A = 
                                Reflect(
                                    {
                                        _x.parent.assign(this)
                                        children.add(_x)
                                    }, _x)
                            class Tuple(_x : String)
                                extends TupleTemplate(_x)
                                    {
                                        parent.assign(__template)
                                        __template.children.add(this)
                                        //System.out.println("Inner tuple %s.".format(_x))
                                    }
                        }
                class Tuple(_x : String)
                    extends TupleTemplate(_x)
                        {
                            //System.out.println("Outer tuple %s.".format(_x))
                        }
                class PropertyValue[A](_x : String)
                    extends PropertyValue.Interface[A]
                        {
                            caption({_ => _x})
                        }
                class Collection[A <: Property.Interface](_x : String)
                    extends Tuple(_x)
                    with PropertyCollection.Interface[A]
                        {
                        }
            }
    }            