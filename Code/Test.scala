package nl.datakneder.run
    {
        import scala.language.implicitConversions
        import scala.language.reflectiveCalls
        import nl.datakneder.projects.PasswordVault._
        import nl.datakneder.temp.UI._
        import nl.datakneder.temp.Persistance._
        import nl.datakneder.temp.Properties._
        import nl.datakneder.core.Properties._
        import nl.datakneder.core.Data.Framework._
        import nl.datakneder.core.Data.Framework.KeyDefinitions._        
        import nl.datakneder.core.UI.Rock.Framework._
        import nl.datakneder.core.Utils.Framework._
        import nl.datakneder.temp.Reflection._
        
        object Test
            {
                def main(args : Array[String])
                    {
                        // Initialise
                            nl.datakneder.core.Utils.Implementation.initialise
                            nl.datakneder.core.Data.Implementation.initialise
                            nl.datakneder.core.UI.Rock.Implementation.initialise
                            nl.datakneder.temp.UI.initialise()
                            nl.datakneder.temp.Persistance.initialise()
                        
                        // Setup
                            // UI
                                //UI.DefaultName
                                //    .add({_ => _ => 
                                //        {case n : Text.Interface => 
                                //            n.caption()
                                //        }})
                                //UI.DefaultComponent
                                //    .add({_ => _ => 
                                //        {case (p : UI.iConstructionData, n : Int) => 
                                //            //System.out.println("Name = %s".format(p.name))
                                //            var content = p.read().toString
                                //            TextField()
                                //                .content.applyCast({() => content})
                                //                .content.update({x => content = x; Try(p.write(content.toInt))})
                                //        }})
                                //    .add({_ => _ => 
                                //        {case (p : UI.iConstructionData, n : Object) if (Reflection.isModule(n)) => 
                                //            val panel = TwoColumnPanel()
                                //            
                                //            val iterator = Reflection.fields(n)
                                //            iterator
                                //                .foreach(
                                //                    {f =>
                                //                        Try(
                                //                            {
                                //                                val name = 
                                //                                    {
                                //                                        val result = UI.DefaultName(f.name())
                                //                                        if (result == "") f.name() else result
                                //                                    }
                                //
                                //                                panel.add(name, 
                                //                                    {
                                //                                        val q = UI.ConstructionData(n, name, {() => f()}, {f(_)})
                                //                                        val result = UI.DefaultComponent((q, f()))
                                //                                        //System.out.println("     component: " + result.getClass.getName)
                                //                                        result
                                //                                    })
                                //                            })
                                //                    })
                                //            
                                //            
                                //            panel
                                //        }})
                                //    .add({_ => _ => 
                                //        {case (p : UI.iConstructionData, n : Text.Interface) => 
                                //            TextField()
                                //                .minimalWidth(200)
                                //                .content.applyCast(n.apply _)
                                //                .content.update(n.apply(_))
                                //        }})
                                //UI.DefaultComponent
                                //    .add({_ => _ => 
                                //        {case (p : UI.iConstructionData, n : Password.Interface) => 
                                //            PasswordField()
                                //                .minimalWidth(200)
                                //                .content.applyCast(n.apply _)
                                //                .content.update(n.apply(_))
                                //        }})
                                //UI.DefaultComponent
                                //    .add({_ => _ => 
                                //        {case (p : UI.iConstructionData, n : FileName.Interface) =>
                                //            val borderPanel = BorderPanel()
                                //            def openFileDialog() : Unit = 
                                //                {
                                //                    val fileChooser = new javax.swing.JFileChooser()
                                //                    Try({fileChooser.setSelectedFile(new java.io.File(n()))})
                                //                    if (fileChooser.showOpenDialog(borderPanel.component) == javax.swing.JFileChooser.APPROVE_OPTION)
                                //                        {
                                //                            n(fileChooser.getSelectedFile.getAbsolutePath)
                                //                        }
                                //                }
                                //            borderPanel
                                //                .keyAction(VK_F2, openFileDialog _)
                                //                .center(
                                //                    TextField()
                                //                        .content.applyCast(n.apply _)
                                //                        .content.update(n.apply(_))
                                //                        .backColor({() => 
                                //                            TryCatch(
                                //                                {
                                //                                    //System.out.println("Color: " + (n(), new java.io.File(n()).exists))
                                //                                    if (new java.io.File(n()).exists || n().size == 0) Some(java.awt.Color.WHITE) else Some(java.awt.Color.RED)
                                //                                }, Some(java.awt.Color.RED))}))
                                //                .east(
                                //                    Button("...")
                                //                        .onClick(openFileDialog _))
                                //                .minimalWidth(200)
                                //        }})
                                //
                                //
                            // Persistance
                                Persistance.DefaultName
                                    .add({_ => _ => 
                                        {case n  => 
                                            TryCatch(n.getClass.getName.cutFrom(".",-1), "UnknownClass")
                                        }})
                                    .add({_ => _ => 
                                        {case n : Text.Interface => 
                                            n.caption()
                                        }})
                                    .add({_ => _ => 
                                        {case x if (Reflection.isModule(x)) => 
                                            Reflection.moduleName(x)
                                        }})
                                Persistance.DefaultXML
                                    .add({_ => _ => 
                                        {case n if (n != null) => 
                                            {<p>{
                                                Reflection.fields(n)
                                                    .map(
                                                        {f => 
                                                            Persistance.DefaultXML(f())
                                                        })
                                            }</p>}.copy(label = Persistance.DefaultName(n))
                                        }})
                                    .add({_ => _ => 
                                        {case n : Text.Interface => 
                                            {<p>{n()}</p>}.copy(label = Persistance.DefaultName(n))
                                        }})
                                    .add({_ => _ => 
                                        {case n : String => 
                                            <String>{n}</String>
                                        }})
                                    .add({_ => _ => 
                                        {case n : Integer => 
                                            <Integer>{n}</Integer>
                                        }})
                                    .add({_ => _ => 
                                        {case n : Boolean => 
                                            <Boolean>{n}</Boolean>
                                        }})
                                Persistance.LoadXML
                                    .add({_ => _ => 
                                        {case x =>
                                            System.out.println("Could not resolve: " + x)
                                        }})
                                    .add({_ => _ => 
                                        {case (_y, _xml : scala.xml.Node) => 
                                            //System.out.println("Generic object found")
                                            Reflection
                                                .fields(_y)
                                                .foreach(
                                                    {f =>
                                                        val label = Persistance.DefaultName(f())
                                                        _xml.child
                                                            .filter(_.label == label)
                                                            .foreach(
                                                                {n => 
                                                                    //System.out.println("%s => %s of type: (%s)".format(n.toString, f.name(), f.typeSignature()))
                                                                    try 
                                                                        {
                                                                            if (Reflection.isModule(f()))
                                                                                Persistance.LoadXML((f(), n))
                                                                            else
                                                                                Persistance.LoadXML((f, n))      
                                                                        } 
                                                                    catch 
                                                                        {
                                                                            case x : Throwable =>
                                                                                x.printStackTrace
                                                                        }
                                                                })
                                                    })
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : Reflection.iField, _xml : scala.xml.Node) if (_f.typeSignature == "java.lang.String")=>
                                            Try(_f(_xml.child(0).toString))
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : Reflection.iField, _xml : scala.xml.Node) if (_f.typeSignature == "Int")=> 
                                            Try(_f(_xml.child(0).toString.toInt))
                                        }})
                        //Password.start()
                        //if (PasswordVault.Settings.edit()) PasswordVault.Settings.save()
      
                        trait iText
                            extends iPropertyValue[String]
                        class Text(_x : String)
                            extends PropertyValue[String](_x)
                            with iText
                                {
                                    read({x => Some(x)})
                                    write({x => x})
                                }
                            
                        implicit def InjectText(_owner : iProperty) = 
                            new Object 
                                {
                                    def Text(_x : String) : iText = 
                                        {
                                            val result = new Text(_x)
                                            result.parent(_owner)
                                            _owner.children.add(result)
                                            result
                                        }
                                    def Text(_x : String, _v : String) : iText = Text(_x)(_v) 
                                }

                        trait iNumber
                            extends iPropertyValue[Int]
                        class Number(_x : String)
                            extends PropertyValue[Int](_x)
                            with iNumber
                                {
                                    read({x => Some(x.toInt)})
                                    write({x => x.toString})
                                }
                            
                        implicit def InjectNumber(_owner : iProperty) = 
                            new Object 
                                {
                                    def Number(_x : String) : iNumber = 
                                        {
                                            val result = new Number(_x)
                                            result.parent(_owner)
                                            _owner.children.add(result)
                                            result
                                        }
                                    def Number(_x : String, _v : Int) : iNumber = Number(_x)(_v) 
                                }
                                
                        // Setup
                            // UI
                                UI.DefaultName
                                    .add({_ => _ => 
                                        {case n : iProperty => 
                                            n.caption()
                                        }})
                                UI.DefaultComponent
                                    .add({_ => _ => 
                                        {case p : iProperty => 
                                            //System.out.println("Name (iProperty) = %s".format(p.caption()))
                                            val panel = TwoColumnPanel()
                                            
                                            val iterator = p.children().iterator
                                            iterator
                                                .foreach(
                                                    {f =>
                                                        val name = UI.DefaultName(f)
                                                        if (name == "") f.caption() else name
                                                        UI.DefaultComponent(f) match
                                                            {
                                                                case c : iAddableComponent =>
                                                                    panel.add(name, c)
                                                                case _ =>
                                                                    panel.add(name, Label("No component defined."))      
                                                            }
                                                    })
                                            panel
                                        }})
                                    .add({_ => _ => 
                                        {case n : iText => 
                                            //System.out.println("Name (Text) = %s".format(n.caption()))
                                            TextField()
                                                .minimalWidth(200)
                                                .content.applyCast(n.apply _)
                                                .content.update(n.apply(_))
                                        }})
                                    .add({_ => _ => 
                                        {case n : iNumber => 
                                            //System.out.println("Name (Text) = %s".format(n.caption()))
                                            TextField()
                                                .minimalWidth(200)
                                                .content.applyCast({() => n.stringValue().getOrElse("")})
                                                .content.update(n.stringValue(_))
                                        }})
                            // Persistance
                                Persistance.DefaultName
                                    .add({_ => _ => 
                                        {case n  => 
                                            TryCatch(n.getClass.getName.cutFrom(".",-1), "UnknownClass")
                                        }})
                                    .add({_ => _ => 
                                        {case n : iProperty => 
                                            n.caption()
                                        }})
                                Persistance.DefaultXML
                                    .add({_ => _ => 
                                        {case n : iProperty => 
                                            {<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                            }</p>}.copy(label = Persistance.DefaultName(n))
                                        }})
                                    .add({_ => _ => 
                                        {case n : iPropertyValue[_] => 
                                            {<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                            }{new scala.xml.Text(n.stringValue().getOrElse(""))}</p>}.copy(label = Persistance.DefaultName(n))
                                    }})
                                Persistance.LoadXML
                                    .add({_ => _ => 
                                        {case x =>
                                            System.out.println("Could not resolve: " + x)
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : iProperty, _xml : scala.xml.Node) =>
                                            _f.children()
                                                .foreach(
                                                    {p => 
                                                        _xml.child
                                                            .filter(_.label == p.caption())
                                                            .foreach({n => Persistance.LoadXML(p, n)})
                                                    })
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : iPropertyValue[_], _xml : scala.xml.Node) =>
                                            Try(
                                                {
                                                    val x = _xml.child.cast({case p : scala.xml.Atom[_] => p.text}).foldLeft("")(_ + _)
                                                    System.out.println("Loading %s = %s".format(_f.caption(), x))
                                                    _f.stringValue(x)
                                                })
                                        }})
                        object Settings
                            extends Tuple("Settings")
                                {
                                    val name = this.Text("Name", "Harry")
                                    val age = this.Number("Age", 20)
                                    object Font
                                        extends Tuple("Font")
                                            {
                                                val name = this.Text("Name", "Comic sans serif")
                                                val size = this.Number("Age", 20)
                                            }
                                    //Font.parent(Some(this))
                                }
                        //Settings.Font
      
                        
                        
                        System.out.println("Settings: " + Settings)
                        System.out.println("Settings.Font.name: " + Settings.Font.name.parents.reverse.map(_.caption()).mkString("-"))
                        System.out.println("Settings.Font.age: " + Settings.Font.size.parents.reverse.map(_.caption()).mkString("-"))

                        Settings.load
                        System.out.println("Settings.name = %s".format(Settings.name()))
                        if (Settings.edit()) Settings.save
                    }
            }
    }