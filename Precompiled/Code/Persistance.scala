package nl.datakneder.temp
    {
        import scala.language.implicitConversions
        import scala.language.reflectiveCalls
 
        import nl.datakneder.core.UI.Rock.Framework._
        import nl.datakneder.core.Data.Framework._
        import nl.datakneder.core.Data.Framework.KeyDefinitions._
        import nl.datakneder.core.Utils.Framework._
        
        
        object Persistance
            {
                implicit def InjectPersistance(_x : Object) =
                    new Object
                        {
                            def load() : Any = 
                                {
                                    Persistance.load(_x)
                                }
                            def save() : Unit = 
                                {
                                    Persistance.save(_x)
                                }
                        }
                object Persistance
                    {
                        trait iConstructionData
                            {
                                val owner : Object
                                val name : String
                                val read : () => Any
                                val write : Any => Unit
                            }
                        case class ConstructionData(owner : Object, name : String, read : () => Any, write : Any => Unit)
                            extends iConstructionData
                        class Convertor[A](_default : () => A)
                            {
                                private var __componentContext = Context[A]({_ => _default()})
                                def add(_f : Context.Interface[A] => Context.Interface[A] => PartialFunction[Any, A]) : this.type = 
                                    {
                                        __componentContext = __componentContext.add(_f)
                                        this
                                    }
                                def apply(_x : Any) : A = __componentContext(_x)
                                
                            }
                        object DefaultName
                            extends Convertor[String]({() => ""})
                                {
                                    
                                }
                        object DefaultXML
                            extends Convertor[scala.xml.Elem]({() => <empty/>})
                                {
                                    
                                }
                        def construct(_x : scala.xml.Elem) : Any = 
                            {
                                ""
                            }
                        def load(_y : Any) : Unit = 
                            {
                            }
                        def save(_x : Any) : Unit = 
                            {
                                System.out.println("Saving...")
                                val xml = DefaultXML(_x)
                                System.out.println(xml)
                                System.out.println(XMLUtils.print(xml))
                            }
                    }
                def initialise() = 
                    {
                    }
            }
    }