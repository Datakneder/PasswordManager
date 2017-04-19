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
                                UI.DefaultPopupMenu
                                    .add({_ => _ => 
                                        {case p : iPropertyCollection[_] => 
                                            //System.out.println("Name (iProperty) = %s".format(p.caption()))
                                            var result = List[iAddableComponent]()
                                            p.construction()
                                                .foreach(
                                                    {c => 
                                                        result = result :+ 
                                                            MenuItem(c.name)
                                                                .onClick(
                                                                    {() => 
                                                                        val n = c.construct().asInstanceOf[iProperty]
                                                                        
                                                                        if (n.edit())
                                                                            {
                                                                                p.add(n)
                                                                            }
                                                                    })
                                                    })
                                            result
                                        }})
                                UI.DefaultComponent
                                    .add({_ => _ => 
                                        {case p : iProperty => 
                                            System.out.println("Name (iProperty) = %s".format(p.caption()))
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
                                        {case n : iPropertyCollection[_] => 
                                            System.out.println("Name (ListBox) = %s".format(n.caption()))
                                            n().filter(_ != null).foreach({p => System.out.println("   " + n.display()(p))})
                                            ListBox()
                                                .items({() => n().map(n.display()(_) + " ")})
                                                .popup(UI.DefaultPopupMenu(n))
                                                //.content.update()
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
                                        {case n : iPropertyCollection[_] => 
                                            {<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                            }<content>{n().map(Persistance.DefaultXML(_))}</content></p>}.copy(label = Persistance.DefaultName(n))
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
                                        {case (_f : iPropertyCollection[_], _xml : scala.xml.Node) =>
                                            _f.children()
                                                .foreach(
                                                    {p => 
                                                        _xml.child
                                                            .filter(_.label == p.caption())
                                                            .foreach({n => Persistance.LoadXML(p, n)})
                                                    })
                                            _xml.child
                                                .filter(_.label == "content")
                                                .foreach(
                                                    {n => 
                                                        _f.clear()
                                                        n.child
                                                            .foreach({n => _f.add(Persistance.ConstructFromXML(_f, n))})
                                                    })
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : iPropertyValue[_], _xml : scala.xml.Node) =>
                                            Try(
                                                {
                                                    val x = _xml.child.cast({case p : scala.xml.Atom[_] => p.text}).foldLeft("")(_ + _)
                                                    //System.out.println("Loading %s = %s".format(_f.caption(), x))
                                                    _f.stringValue(x)
                                                })
                                        }})
                                    Persistance.ConstructFromXML
                                        .add({_ => _ => 
                                            {case (_, _xml : scala.xml.Node) if (_xml.label == "Location") =>
                                                    System.out.println("Constructing Location.")
                                                    val result = new Location()
                                                    Persistance.LoadXML(result, _xml)
                                                    result
                                            }})
                        object Settings
                            extends Tuple("Settings")
                                {
                                    val name = this.Text("Name", "Harry")
                                    val age = this.Number("Age", 20)
                                    val locations = this.Collection[Location]("Locations")
                                    locations.display({n => n.name()})
                                    locations.construction.add(Constructor("Add Location", {() => new Location()}))
                                    locations.add(
                                        {
                                            val result = new Location
                                            result.name("Henk")
                                            result.url("https://henk.nl/login")
                                            result
                                        })
                                    object Font
                                        extends Tuple("Font")
                                            {
                                                val name = this.Text("Name", "Comic sans serif")
                                                val size = this.Number("Age", 20)
                                            }
                                    Font
                                }
                        class Location
                            extends Tuple("Location")
                                {
                                    val name = this.Text("Name", "")
                                    val url = this.Text("URL", "")
                                }
                        class PasswordData
                            extends Tuple("Data")
                                {
                                    val mandatory = this.Text("Mandatory", "")
                                    val alphabetPattern = this.Text("Alphabet", "")
                                    val user = this.Text("User","")
                                    val key = this.Text("Key","")
                                    val size = this.Number("Size",40)
                                    //val locations = this.Collection("Locations")
                                }
                        System.out.println("Settings.locations.size = %d".format(Settings.locations.size))
                        Settings.load
                        System.out.println("Settings.locations.size = %d".format(Settings.locations.size))
                        System.out.println("    name = " + Settings.locations()(0).name())
                        System.out.println("    url = " + Settings.locations()(0).url())
                        if (Settings.edit()) Settings.save
                    }
            }
    }