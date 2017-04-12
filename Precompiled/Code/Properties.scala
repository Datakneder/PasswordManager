package nl.datakneder.core
    {
        import nl.datakneder.core.Utils.Framework._
        
        object Properties
            {
                // Properties
                    // Text
                        object Text
                            {
                                trait Interface 
                                    {
                                        def caption() : String 
                                        def caption(_x : String) : this.type 
                                        def apply() : String 
                                        def apply(_x : String) : this.type 
                                    }
                                class Class()
                                    extends Interface
                                        {
                                            private var __caption : String = ""
                                            def caption() : String = __caption
                                            def caption(_x : String) : this.type = Reflect(__caption = _x, this)
                                            private var __content : String = ""
                                            def apply() : String = __content
                                            def apply(_x : String) : this.type = Reflect(__content = _x, this) 
                                        }
                                def apply(_x : String) : Interface = new Class().caption(_x)
                            }
                    // Password
                        object Password
                            {
                                trait Interface
                                    extends Text.Interface
                                    {
                                    }
                                class Class()
                                    extends Text.Class() 
                                    with Interface
                                        {
                                        }
                                def apply(_x : String) : Interface = new Class().caption(_x)
                            }
                    // FileName
                        object FileName
                            {
                                trait Interface
                                    extends Text.Interface
                                    {
                                    }
                                class Class()
                                    extends Text.Class() 
                                    with Interface
                                        {
                                        }
                                def apply(_x : String) : Interface = new Class().caption(_x)
                            }
            }
    }