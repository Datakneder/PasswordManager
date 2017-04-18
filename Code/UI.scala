package nl.datakneder.temp
    {
        import scala.language.implicitConversions
        import scala.language.reflectiveCalls
 
        import nl.datakneder.core.UI.Rock.Framework._
        import nl.datakneder.core.Data.Framework.KeyDefinitions._
        import nl.datakneder.core.Utils.Framework._
        
        
        object UI
            {
                implicit def InjectEdit(_x : Object) =
                    new Object
                        {
                            def edit(_names : String*) : Boolean = 
                                {
                                    UI.edit(_x, _names :_*)
                                }
                        }

                object UI
                    {
                        //trait iConstructionData
                        //    {
                        //        val owner : Object
                        //        val name : String
                        //        val read : () => Any
                        //        val write : Any => Unit
                        //    }
                        //case class ConstructionData(owner : Object, name : String, read : () => Any, write : Any => Unit)
                        //    extends iConstructionData
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
                        object DefaultComponent
                            extends Convertor[iAddableComponent]({() => Label("Has no component defined.")})
                                {
                                    
                                }
                        object DefaultPanel
                            extends Convertor[iAddableComponent]({() => Label("Has no panel defined.")})
                                {
                                    
                                }
                        object DefaultMenu
                            extends Convertor[List[iAddableComponent]]({() => List[iAddableComponent]()})
                                {
                                    
                                }
                        object DefaultPopupMenu
                            extends Convertor[List[iAddableComponent]]({() => List[iAddableComponent]()})
                                {
                                    
                                }
                        def editPanel(_x : Object, _names : String*) : iAddableComponent =
                            {
                                import nl.datakneder.temp.Reflection._
                                
                                DefaultComponent(_x) match
                                    {
                                        case p : iAddableComponent =>
                                            p
                                        case _ =>
                                            null
                                    }
                                
                                //val panel = TwoColumnPanel()
                                //
                                //val iterator = Reflection.fields(_x, _names :_*)
                                //iterator
                                //    .foreach(
                                //        {f =>
                                //            Try(
                                //                {
                                //                    val name = 
                                //                        {
                                //                            val result = DefaultName(f.name())
                                //                            if (result == "") f.name() else result
                                //                        }
                                //
                                //                    panel.add(name, 
                                //                        {
                                //                            val p = ConstructionData(_x, name, {() => f()}, {f(_)})
                                //                            val result = DefaultComponent((p, f()))
                                //                            //System.out.println("     component: " + result.getClass.getName)
                                //                            result
                                //                        })
                                //                })
                                //        })
                                //panel
                            }
                        def edit(_x : Object, _names : String*) : Boolean = 
                            {
                                val dialog = Dialog()
                                var result = false
                                def ok() = 
                                    {
                                        result = true
                                        dialog.close()
                                    }
                                def cancel() = 
                                    {
                                        dialog.close()
                                    }
                                val menu = DefaultMenu(_x)
                                if (menu.size > 0) 
                                    {
                                        dialog
                                            .menu(
                                                MenuBar(
                                                    {menuBar =>
                                                        menu.foreach(menuBar.add)
                                                    }))
                                    }
                                dialog
                                    .keyAction(VK_Escape, cancel _)
                                    .keyAction(VK_Enter, ok _)
                                    .add(
                                            BorderPanel()
                                                .center(editPanel(_x, _names :_*))
                                                .south(
                                                        DistributedPanel()
                                                            .horizontal()
                                                            .add(
                                                                    Button("Cancel")
                                                                        .onClick(cancel _)
                                                                )
                                                            .add(
                                                                    Button("Ok")
                                                                        .onClick(ok _)
                                                                )
                                                            .margin(10)
                                                    )
                                        )
                                    .show()
                                result
                            }
                    }
                def initialise() = 
                    {
                        //UI.DefaultComponent
                        //    .add({_ => _ => 
                        //        {case (p : UI.iConstructionData, n : String) => 
                        //            //System.out.println("Name = %s".format(p.name))
                        //            TextField()
                        //                .content.applyCast(p.read)
                        //                .content.update(p.write)
                        //        }})
                        
                    }
            }
    }