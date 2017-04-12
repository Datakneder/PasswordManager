package nl.datakneder.run
    {
        import scala.language.reflectiveCalls
        import nl.datakneder.projects.PasswordVault._
        import nl.datakneder.temp.UI._
        import nl.datakneder.temp.Persistance._
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
                                Persistance.DefaultXML
                                    .add({_ => _ => 
                                        {case n => 
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
                        //Password.start()
                        if (PasswordVault.Settings.edit()) PasswordVault.Settings.save()
                        
                    }
            }
    }