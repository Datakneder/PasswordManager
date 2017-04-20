package nl.datakneder.temp
    {
        import scala.language.implicitConversions
        import nl.datakneder.core.Utils.Framework._
        
        object Properties
            {
                class Variable[A, B](_n : A, _owner : B)
                    {
                        private var __value : A = _n
                        def apply() : A = __value
                        def apply(_x : A) : B = 
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
                        val caption = new Variable("", this)
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
                        {
                            private var __stringValue : Option[String] = None
                            def stringValue() : Option[String] = 
                                {
                                    if (__stringValue == None && __value != None)
                                        {
                                            __stringValue = TryCatch(Some(__write(__value.get)),None)
                                            System.out.println("$s.stringValue is set to %s.".format(caption(), __stringValue.toString))
                                        }
                                    __stringValue
                                }
                            def stringValue(_x : Option[String]) : this.type = 
                                Reflect(
                                        {
                                            __stringValue = _x
                                            __value = None
                                        }, this)
                            def stringValue(_x : String) : this.type = stringValue(Some(_x))
                            private var __value : Option[A] = None
                            def value() : Option[A] = __value
                            def apply(_x : A) : this.type = 
                                Reflect(
                                    {
                                        __value = Some(_x)
                                        __stringValue = None
                                    }, this)
                            
                            private var __default : Option[A] = None
                            private var __read : String => Option[A] = {_ => None}
                            def read(_x : String => Option[A]) : this.type = Reflect(__read = _x, this)
                            private var __write : A => String = {x => x.toString}
                            def write(_f : A => String) : this.type = Reflect(__write = _f, this)
                            def default() : Option[A] = __default
                            def apply() : A = 
                                {
                                    if (__value == None)
                                        {
                                            Try({__value = __read(__stringValue.get)})
                                        }
                                    __value.getOrElse(__default.get)
                                }
                            def default(_x : A) : this.type = Reflect(__default = Some(_x), this)
                        }
                case class Constructor[A](name : String, construct : () => A)
                trait iPropertyCollection[A <: iProperty]
                    extends iProperty
                        {
                            private val __value = new VariableList[A, this.type](this)
                            def apply() : List[A] = __value()
                            def add(_x : Any) : this.type = 
                                Reflect(
                                    {
                                        Try(if (_x != null) __value.add(_x.asInstanceOf[A]))
                                    }, this)
                            def remove(_x : A) : this.type = __value.remove(_x)
                            def clear() : this.type = __value.clear()
                            def size() : Int = __value.size
                            val display = new Variable[A => String, this.type]({_.toString}, this)
                            val construction = new VariableList[Constructor[_], this.type](this)
                            def stringList() : List[String] = __value().map(display()(_))
                            def selection() : iPropertySelection[A] = 
                                {
                                    val result = new iPropertySelection[A] {}
                                    result.collection(Some(this))
                                    result
                                }
                            def swap(_i : Int, _j : Int) : this.type = Reflect({__value(Lists.swap(_i, _j, __value()))}, this)
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
                            val singleSelection = new Variable(false, this)
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
                            var collection = new Variable[Option[iPropertyCollection[A]], this.type](None, this)
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