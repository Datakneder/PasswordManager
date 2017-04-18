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
                            private var __value : Option[A] = None
                            private var __default : Option[A] = None
                            def value() : Option[A] = __value
                            def default() : Option[A] = __default
                            def apply() : A = __value.getOrElse(__default.get)
                            def apply(_x : A) : this.type = Reflect(__value = Some(_x), this)
                            def default(_x : A) : this.type = Reflect(__default = Some(_x), this)
                        }
                class TupleTemplate(_x : String)
                    extends iProperty
                        {__template =>
                            caption(_x)
                            class Tuple(_x : String)
                                extends TupleTemplate(_x)
                                    {
                                        parent(__template)
                                    }
                        }
                class Tuple(_x : String)
                    extends TupleTemplate(_x)
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
                                    result
                                }
                            def PropertyValue[A](_x : String, _v : A) : iPropertyValue[A] = PropertyValue(_x)(_v)
                        }
            }
    }