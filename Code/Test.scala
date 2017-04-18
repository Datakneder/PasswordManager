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
                                UI.DefaultName
                                    .add({_ => _ => 
                                        {case n : Text.Interface => 
                                            n.caption()
                                        }})
                                UI.DefaultComponent
                                    .add({_ => _ => 
                                        {case (p : UI.iConstructionData, n : Int) => 
                                            //System.out.println("Name = %s".format(p.name))
                                            var content = p.read().toString
                                            TextField()
                                                .content.applyCast({() => content})
                                                .content.update({x => content = x; Try(p.write(content.toInt))})
                                        }})
                                    .add({_ => _ => 
                                        {case (p : UI.iConstructionData, n : Object) if (Reflection.isModule(n)) => 
                                            val panel = TwoColumnPanel()
                                            
                                            val iterator = Reflection.fields(n)
                                            iterator
                                                .foreach(
                                                    {f =>
                                                        Try(
                                                            {
                                                                val name = 
                                                                    {
                                                                        val result = UI.DefaultName(f.name())
                                                                        if (result == "") f.name() else result
                                                                    }
            
                                                                panel.add(name, 
                                                                    {
                                                                        val q = UI.ConstructionData(n, name, {() => f()}, {f(_)})
                                                                        val result = UI.DefaultComponent((q, f()))
                                                                        //System.out.println("     component: " + result.getClass.getName)
                                                                        result
                                                                    })
                                                            })
                                                    })
                                            
                                            
                                            panel
                                        }})
                                    .add({_ => _ => 
                                        {case (p : UI.iConstructionData, n : Text.Interface) => 
                                            TextField()
                                                .minimalWidth(200)
                                                .content.applyCast(n.apply _)
                                                .content.update(n.apply(_))
                                        }})
                                UI.DefaultComponent
                                    .add({_ => _ => 
                                        {case (p : UI.iConstructionData, n : Password.Interface) => 
                                            PasswordField()
                                                .minimalWidth(200)
                                                .content.applyCast(n.apply _)
                                                .content.update(n.apply(_))
                                        }})
                                UI.DefaultComponent
                                    .add({_ => _ => 
                                        {case (p : UI.iConstructionData, n : FileName.Interface) =>
                                            val borderPanel = BorderPanel()
                                            def openFileDialog() : Unit = 
                                                {
                                                    val fileChooser = new javax.swing.JFileChooser()
                                                    Try({fileChooser.setSelectedFile(new java.io.File(n()))})
                                                    if (fileChooser.showOpenDialog(borderPanel.component) == javax.swing.JFileChooser.APPROVE_OPTION)
                                                        {
                                                            n(fileChooser.getSelectedFile.getAbsolutePath)
                                                        }
                                                }
                                            borderPanel
                                                .keyAction(VK_F2, openFileDialog _)
                                                .center(
                                                    TextField()
                                                        .content.applyCast(n.apply _)
                                                        .content.update(n.apply(_))
                                                        .backColor({() => 
                                                            TryCatch(
                                                                {
                                                                    //System.out.println("Color: " + (n(), new java.io.File(n()).exists))
                                                                    if (new java.io.File(n()).exists || n().size == 0) Some(java.awt.Color.WHITE) else Some(java.awt.Color.RED)
                                                                }, Some(java.awt.Color.RED))}))
                                                .east(
                                                    Button("...")
                                                        .onClick(openFileDialog _))
                                                .minimalWidth(200)
                                        }})
                    
                            
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
      
                        object Settings
                            extends Tuple("Settings")
                                {
                                    val name = this.PropertyValue("Name", "Harry")
                                    val age : Int = 20
                                    object Font
                                        extends Tuple("Font")
                                            {
                                                val name = this.PropertyValue("Name", "Comic sans serif")
                                                val size  = 20
                                            }
                                    //Font.parent(Some(this))
                                }
                        Settings.Font
      
                        System.out.println("Settings: " + Settings)
                        System.out.println("Settings.Font.name: " + Settings.Font.name.parents.map(_.caption()).mkString("-"))
                        System.out.println("Settings.name: " + Settings.name.parents.map(_.caption()).mkString("-"))
                        System.out.println(Settings.name.parent())
                        System.exit(0)
                        
                        //System.out.println("Font: " + Settings.Font)
                        //Reflection.fields(Settings)
                        //    .foreach({f => System.out.println("%s: %s".format(f.name, f().toString))})
                        //System.out.println("--------------------")
                        Settings.load
                        if (Settings.edit()) Settings.save
                    }
            }
    }