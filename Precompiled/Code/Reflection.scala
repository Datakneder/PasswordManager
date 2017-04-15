package nl.datakneder.temp
    {
        import scala.language.reflectiveCalls
        import nl.datakneder.core.Utils.Framework._
        
        object Reflection
            {
                object Reflection
                    {
                        trait iField
                            {
                                def name() : String
                                def apply() : Any
                                def apply(_x : Any) 
                                def typeSignature() : String
                            }
                        class Field(_im : reflect.runtime.universe.InstanceMirror, _f : reflect.runtime.universe.TermSymbol)
                            extends iField
                                {
                                    lazy val fieldMirror = _im.reflectField(_f)
                                    def name() : String = _f.name.toString.trim
                                    def apply() : Any = fieldMirror.get       
                                    def apply(_x : Any) : Unit = fieldMirror.set(_x)
                                    def typeSignature() : String = _f.typeSignature.toString
                                }
                        def fields(_x : Any, _names : String*) : List[iField] = 
                            {
                                import scala.reflect.runtime.{universe => ru}
                                import scala.reflect.runtime.currentMirror
                                val m = ru.runtimeMirror(_x.getClass.getClassLoader)
                                val im = m.reflect(_x)
                                //System.out.println("y = " + _x.getClass.getName)
                                val list = 
                                    currentMirror
                                        .classSymbol((_x).getClass)
                                        .toType
                                        .members
                                        .filter(!_.isMethod)
                                        .filter(!_.isModule)
                                        .filter(_.isTerm)
                                        .map(_.asTerm)
                                        .toList
                                //list.foreach({f => System.out.println("   " + f.name.toString)})
                                if (_names.size != 0)
                                    {
                                        _names
                                            .map(
                                                {n => 
                                                    list
                                                        .filter(
                                                            {f =>
                                                                //System.out.println("Field: (/%s/, /%s/, /%b/)".format(f.name.toString, n, f.name.toString.equals(n)))
                                                                f.name.toString.trim.equals(n)
                                                            })
                                                        .take(1)
                                                        .nextOrElse(null)
                                                })
                                            .filter(_ != null)
                                            .iterator.toList
                                    } else 
                                    {
                                        list
                                            .reverseIterator.toList
                                    }
                                
                                list.map({f => new Field(im, f)})
                            }
                        def isModule(_x : Any) : Boolean =
                            {
                                TryCatch(_x.getClass.getName.endsWith("$"), false)
                            }
                        def moduleName(_x : Any) : String = 
                            {
                                if (_x.getClass.getEnclosingClass != null) return _x.getClass.getName.cutFrom(_x.getClass.getEnclosingClass.getName).cutTo("$")
                                _x.getClass.getName.cutFrom(".",-1).replace("$","")
                            }
                    }
                
            }
    }