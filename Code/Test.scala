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
                        //implicit def InjectXMLFunctions(_xml : scala.xml.Node) = 
                        //    new Object
                        //        {
                        //            def labelsToCaptions() : scala.xml.Node = labelsToCaptions(_xml)
                        //            def labelsToCaptions(_xml : scala.xml.Node) : scala.xml.Node = 
                        //                {
                        //                    _xml
                        //                }
                        //            def captionsToLabels() : scala.xml.Node = 
                        //            def captionsToLabels(_xml : scala.xml.Node) : scala.xml.Node = 
                        //                {
                        //                    _xml
                        //                }
                        //        }
                        
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
                                        {case p : iPropertySelection[_] => 
                                            //System.out.println("Name (iProperty) = %s".format(p.caption()))
                                            var result = List[iAddableComponent]()
                                            val collection = p.collection().get
                                            collection.construction()
                                                .foreach(
                                                    {c => 
                                                        result = result :+ 
                                                            MenuItem(c.name)
                                                                .onClick(
                                                                    {() => 
                                                                        val n = c.construct().asInstanceOf[iProperty]
                                                                        
                                                                        if (n.edit())
                                                                            {
                                                                                collection.add(n)
                                                                            }
                                                                    })
                                                    })
                                            result = result :+ Separator()
                                            result = result :+ MenuItem("Delete").onClick({() => p.removeFromCollection()}).accelerator(ALT + VK_Delete)
                                            result = result :+ Separator()
                                            result = result :+ MenuItem("Edit").onClick({() => p.selectedItems().take(1).foreach(_.edit())}).accelerator(VK_F2)
                                            result = result :+ Separator()
                                            
                                            result = result :+ MenuItem("Move Up").onClick({() => p.moveUp()}).accelerator(ALT + VK_Up)
                                            result = result :+ MenuItem("Move Down").onClick({() => p.moveDown()}).accelerator(ALT + VK_Down)
                                            result
                                        }})
                                UI.DefaultComponent
                                    .add({_ => _ => 
                                        {case p : iProperty => 
                                            //System.out.println("Name (iProperty) = %s".format(p.caption()))
                                            val panel = TwoColumnPanel()
                                            
                                            val iterator = p.children().iterator
                                            //System.out.println("AA Size = %d".format(p.children().size))
                                            iterator
                                                .foreach(
                                                    {f =>
                                                        //System.out.println("    adding '%s'".format(f.caption()))
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
                                        {case n : iPropertySelection[_] =>
                                            val collection = n.collection().get
                                            //System.out.println("Name (ListBox) = %s".format(n.caption()))
                                            //n().foreach({p => System.out.println("   " + p)})
                                            
                                            if (n.singleSelection()) 
                                                {
                                                    
                                                    ComboBox()
                                                        .items({() => collection.stringList()})
                                                        .popup(UI.DefaultPopupMenu(n))
                                                        .content(
                                                            {() =>
                                                                //System.out.println("Content: '%s'".format(n().nextOrElse("")))
                                                                n().nextOrElse("")
                                                            })
                                                        .content.update({x => n(List(x))})
                                                } 
                                            else 
                                                {
                                                    ListBox()
                                                        .items({() => collection.stringList()})
                                                        .popup(UI.DefaultPopupMenu(n))
                                                        .content({() => n()})
                                                        .content.update({x => n(x)})
                                                }
                                        }})
                                    .add({_ => _ => 
                                        {case n : iPropertyCollection[_] =>
                                            UI.DefaultComponent(n.selection())
                                        }})
                                    .add({_ => _ => 
                                        {case n : iText => 
                                            //System.out.println("Name (Text) = %s".format(n.caption()))
                                            if (n.isCalculated()) 
                                                {
                                                    Label()
                                                        .caption({() => n.stringValue().getOrElse("")})
                                                } 
                                            else 
                                                {
                                                    TextField()
                                                        .minimalWidth(200)
                                                        .content.applyCast(n.apply _)
                                                        .content.update(n.apply(_))
                                                }
                                        }})
                                    .add({_ => _ => 
                                        {case n : iNumber => 
                                            //System.out.println("Name (Text) = %s".format(n.caption()))
                                            if (n.isCalculated()) 
                                                {
                                                    Label()
                                                        .caption({() => n.stringValue().getOrElse("")})
                                                } 
                                            else 
                                                {
                                                    TextField()
                                                        .minimalWidth(200)
                                                        .content.applyCast({() => n.stringValue().getOrElse("")})
                                                        .content.update(n.stringValue(_))
                                                }
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
                                            XMLUtils.label({<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                            }</p>},Persistance.DefaultName(n))
                                        }})
                                    .add({_ => _ => 
                                        {case n : iPropertyCollection[_] => 
                                            XMLUtils.label({<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                            }<content>{n().map(Persistance.DefaultXML(_))}</content></p>},Persistance.DefaultName(n))
                                        }})
                                    .add({_ => _ => 
                                        {case n : iPropertyValue[_] if (!n.isCalculated())=>
                                            XMLUtils.label({<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                            }{new scala.xml.Text(n.stringValue().getOrElse(""))}</p>}, Persistance.DefaultName(n))
                                        }})
                                    .add({_ => _ => 
                                        {case n : iPropertySelection[_] => 
                                            XMLUtils.label({<p>{
                                                n.children().map({c => Persistance.DefaultXML(c)})
                                                    }<content>{n().map({s => <item>{s}</item>})}</content></p>}, Persistance.DefaultName(n))
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
                                                        //System.out.println("Loading (%s)".format(p.caption()))
                                                        //System.out.println("Loading (%s, %s)".format(p.caption(), XMLUtils.label(p.caption())))
                                                        _xml.child
                                                            .filter(_.label == XMLUtils.label(p.caption()))
                                                            .foreach(
                                                                {n =>
                                                                    Persistance.LoadXML(p, n)
                                                                })
                                                    })
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : iPropertyCollection[_], _xml : scala.xml.Node) =>
                                            _f.children()
                                                .foreach(
                                                    {p => 
                                                        _xml.child
                                                            .filter(_.label == XMLUtils.label(p.caption()))
                                                            .foreach({n => Persistance.LoadXML(p, n)})
                                                    })
                                            _xml.child
                                                .filter(_.label == "content")
                                                .foreach(
                                                    {n => 
                                                        _f.clear()
                                                        n.child
                                                            .foreach(
                                                                {n =>
                                                                    //System.out.println("Trying to construct: %s".format(n.label))
                                                                    _f.add(Persistance.ConstructFromXML(_f, n))
                                                                })
                                                    })
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : iPropertySelection[_], _xml : scala.xml.Node) =>
                                            _f.children()
                                                .foreach(
                                                    {p => 
                                                        _xml.child
                                                            .filter(_.label == XMLUtils.label(p.caption()))
                                                            .foreach({n => Persistance.LoadXML(p, n)})
                                                    })
                                            _xml.child
                                                .filter(_.label == "content")
                                                .foreach(
                                                    {n => 
                                                        _f.clear()
                                                        n.child
                                                            .foreach(
                                                                {n =>
                                                                    //System.out.println("Trying to construct: %s".format(n.label))
                                                                    _f.add(n.child.iterator.cast({case p : scala.xml.Atom[_] => p.text}).foldLeft("")(_ + _))
                                                                })
                                                    })
                                        }})
                                    .add({_ => _ => 
                                        {case (_f : iPropertyValue[_], _xml : scala.xml.Node) if (!_f.isCalculated())=>
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
                                                    //System.out.println("Constructing Location.")
                                                    val result = new Location()
                                                    Persistance.LoadXML(result, _xml)
                                                    result
                                            }})
                                        .add({_ => _ => 
                                            {case (_, _xml : scala.xml.Node) if (_xml.label == "Data") =>
                                                    //System.out.println("Constructing Data.")
                                                    val result = new PasswordData()
                                                    Persistance.LoadXML(result, _xml)
                                                    result
                                            }})
                        object Settings
                            extends Tuple("Settings")
                                {
                                    val name = this.Text("Name", "Harry")
                                    val age = this.Number("Age", 20)
                                    val data = this.Collection[PasswordData]("Data")
                                    data.display({n => n.name()})
                                    data.construction.add(Constructor("Add Data", {() => new PasswordData()}))
                                    val locations = new Collection[Location]("Location")({n => data(n).map(_.locations()).foldLeft(List[Location]())(_ ++ _)}).dependencies(data)
                                    locations.display(_.name())
                                    val location = locations.selection()
                                    location.caption("Location")
                                    location.singleSelection(true)
                                    location.parent(this)
                                    children.add(location)
                                    
                                }
                        //System.out.println("Settings.location: " + Settings.location.parents.map(_.caption()).mkString("-"))
                        class Location
                            extends Tuple("Location")
                                {
                                    val name = this.Text("Name", "")
                                    val url = this.Text("URL", "")
                                }
                        class PasswordData
                            extends Tuple("Data")
                                {
                                    val name = this.Text("Name","")
                                    val mandatory = this.Text("Mandatory", "")
                                    val alphabetPattern = this.Text("Alphabet", "\\w\\d")
                                    val alphabet = this.Text(" ", " not defined.")
                                        alphabet(
                                            {n => 
                                                TryCatch(
                                                    {
                                                        val pattern = java.util.regex.Pattern.compile("[" + alphabetPattern() + "]")
                                                        Some(Range(255).map("" + _.toChar).filter({c => pattern.matcher(c).matches}).mkString)
                                                        //val pattern = java.util.regex.Pattern.compile("[" + alphabetPattern() + "]")
                                                        //Some(
                                                        //    Range(255)
                                                        //        .map({i => i.toByte.asInstanceOf[Char]})
                                                        //        .filter(pattern.matcher(_).matches)
                                                        //        .mkString)
                                                    }, {Some("undefined.")})
                                            })
                                        .dependencies(alphabetPattern)
                                        //.parent(Some(this))
                                        //children.add(alphabet)
                                    val user = this.Text("User","")
                                    val key = this.Text("Key","")
                                    val size = this.Number("Size",40)
                                    val locations = this.Collection[Location]("Locations")
                                    locations.display({n => n.name()})
                                    locations.construction.add(Constructor("Add Location", {() => new Location()}))
                                }
                        Settings.load
                        //System.out.println(Settings.location.selectedItems.take(1))
                        if (Settings.edit()) Settings.save
                    }
            }
    }