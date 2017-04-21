package nl.datakneder.temp
    {
        import scala.language.implicitConversions
        import nl.datakneder.core.Utils.Framework._
        import nl.datakneder.temp.Calculation._
        
        object Properties
            {
                class Variable[A, B](_n : () => A, _owner : B)
                    {
                        private var __value : () => A = _n
                        def apply() : A = __value()
                        def apply(_x : A) : B = apply({() => _x}) 
                        def apply(_x : () => A) : B = 
                            {
                                __value = _x
                                _owner
                            }
                    }
                class OptionalVariable[A, B](_owner : B)
                    {
                        private var __value : Option[A] = None
                        def apply() : Option[A] = __value
                        def apply(_x : Option[A]) : B = 
                            {
                                __value = _x
                                _owner
                            }
                        def apply(_x : A) : B = apply(Some(_x))
                    }
                class VariableList[A, B](_owner : B)
                    {
                        private var __value : List[A] = List()
                        def apply() : List[A] = __value
                        def apply(_x : List[A]) : B = 
                            {
                                __value = _x
                                _owner
                            }
                        def add(_x : A) : B = apply(apply() :+ _x)
                        def remove(_x : A) : B = apply(apply().filter(_ != _x))
                        def clear() : B = apply(List())
                        def size() : Int = apply().size
                    }
                trait iProperty
                    {
                        val caption = new Variable({() => ""}, this)
                        val parent = new OptionalVariable[iProperty, iProperty](this)
                        def parents() : List[iProperty] = parents(true)
                        def parents(_x : Boolean) : List[iProperty] = 
                            {
                                var result : List [iProperty] = if (_x) List(this) else List()
                                result ++ parent().map(_.parents(true)).getOrElse(List())
                            }
                        val children = new VariableList[iProperty, iProperty](this)
                    }
                trait iPropertyValue[A]
                    extends iProperty
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
                case class Constructor[A](name : String, construct : () => A)
                trait iPropertyCollection[A <: iProperty]
                    extends iProperty
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
                            val construction = new VariableList[Constructor[_], this.type](this)
                            def display(_x : A => String) : this.type = Reflect(__display({_ => _x}), this)
                            def stringList() : List[String] = __stringList()
                            def selection() : iPropertySelection[A] = 
                                {
                                    val result = new iPropertySelection[A] {}
                                    result.collection(Some(this))
                                    result
                                }
                            def swap(_i : Int, _j : Int) : this.type = 
                                Reflect(
                                    {
                                        val l = Lists.swap(_i, _j, __value())
                                        __value({_ => l})
                                    }, this)
                        }
                trait iPropertySelection[A <: iProperty]
                    extends iProperty
                        {
                            private val __value = new VariableList[String, this.type](this)
                            def apply() : List[String] = __value()
                            def apply(_x : List[String]) : this.type = Reflect(__value(_x), this)
                            def add(_x : String) : this.type = 
                                Reflect(
                                    {
                                        Try(if (_x != null) __value.add(_x))
                                    }, this)
                            def remove(_x : String) : this.type = __value.remove(_x)
                            def clear() : this.type = __value.clear()
                            def size() : Int = __value.size
                            val singleSelection = new Variable({() => false}, this)
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
                            var collection = new Variable[Option[iPropertyCollection[A]], this.type]({() => None}, this)
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
                class TupleTemplate(_x : String)
                    extends iProperty
                        {__template =>
                            caption(_x)
                            class Tuple(_x : String)
                                extends TupleTemplate(_x)
                                    {
                                        parent(__template)
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
                    extends iPropertyValue[A]
                        {
                            caption(_x)
                        }
                implicit def InjectPropertyValue(_owner : iProperty) = 
                    new Object 
                        {
                            def PropertyValue[A](_x : String) : iPropertyValue[A] = 
                                {
                                    val result = new PropertyValue[A](_x)
                                    result.parent(Some(_owner))
                                    _owner.children.add(result)
                                    result
                                }
                            def PropertyValue[A](_x : String, _v : A) : iPropertyValue[A] = PropertyValue(_x)(_v)
                        }
                class Collection[A <: iProperty](_x : String)
                    extends Tuple(_x)
                    with iPropertyCollection[A]
                        {
                        }
                implicit def InjectPropertyCollection(_owner : iProperty) = 
                    new Object 
                        {
                            def Collection[A <: iProperty](_x : String) : iPropertyCollection[A] = 
                                {
                                    val result = new Collection[A](_x)
                                    result.parent(Some(_owner))
                                    _owner.children.add(result)
                                    result
                                }
                        }
            }
    }