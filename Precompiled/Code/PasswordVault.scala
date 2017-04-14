package nl.datakneder.projects.PasswordVault
    {
        import scala.language.implicitConversions
        import scala.language.reflectiveCalls
        import nl.datakneder.core.Data.Framework.KeyDefinitions._
        import nl.datakneder.core.Utils.Framework._
        import nl.datakneder.core.Properties._
        
        object PasswordVault
            {
                import nl.datakneder.core.UI.Rock.Framework._
                import nl.datakneder.temp.UI._
                
                
                // Settings
                    object Settings
                        {
                            var separator = Text("Separator")(";;;")
                            var newLine = Text("New Line")("\\n")
                            var browser = FileName("Browser")("C:/Program Files (x86)/Mozilla Firefox/firefox.exe")
                        }
                // Application
                    class Application(var owner : Option[Location], var name : String, var url : String)
                        {
                            def login(_pw : String) : Unit =
                                {
                                    owner.foreach(
                                        {owner =>
                                            ClipBoard(owner.user + Settings.separator + owner.encrypt(_pw))
                                            Runtime.getRuntime.exec(Settings.browser() + " " + url)
                                            System.out.println("Open " + url)
                                        })
                                }
                        }
                // Location
                    class Location(var name : String, var user : String, var alphabetPattern : String, var seed : String, var size : Int, var mandatory : String)
                        {
                            private var __applications : List[Application] = List()
                            def applications() : List[Application] = __applications
                            def add(_x : Application*) : this.type = 
                                {
                                    _x.foreach(
                                        {n => 
                                            __applications = __applications :+ n
                                            n.owner = Some(this)
                                        })
                                    this
                                }
                            def newApplication() : Unit =
                                {
                                    val dialog = Dialog()
                                    val na = new Application(None, "", "")
                                    dialog
                                        .caption("New Application")
                                        .keyAction(VK_Enter, 
                                            {() =>
                                                add(na)
                                                dialog.close
                                            })
                                        .keyAction(VK_Escape, {() => dialog.close})
                                        .add(
                                            TwoColumnPanel()
                                                .add("Name", 
                                                    TextField()
                                                        .content({() => na.name})
                                                        .content.update({x => na.name = x}))
                                                .add("URL", 
                                                    TextField()
                                                        .content({() => na.url})
                                                        .content.update({x => na.url = x}))
                                            )
                                        .show()
                                }
                            def alphabet() : String = 
                                {
                                    TryCatch(
                                        {
                                            val pattern = java.util.regex.Pattern.compile("[" + alphabetPattern + "]")
                                            Range(255).map("" + _.toChar).filter({c => pattern.matcher(c).matches}).mkString
                                        }, "")
                                }
                            def encrypt(_seed : String) : String = 
                                {
                                    val base = new java.math.BigInteger(seed.getBytes()).nextProbablePrime
                                    //System.out.println("Base = " + base)
                                    val exponent = base.bitLength + 5
                                    //System.out.println("Exponent = " + exponent)
                                    var index = new java.math.BigInteger("2").pow(exponent/2) 
                                    //System.out.println("Index = " + index)
                                    
                                    val alph = alphabet() 
                                    
                                    var s = _seed
                                    while (s.size < size) s += s
                                    
                                    mandatory + 
                                    s
                                        .take(size - mandatory.size)
                                        .getBytes()
                                        .map(
                                            {c => 
                                                val n = index.pow(exponent).mod(base).add(new java.math.BigInteger("" + c)).mod(new java.math.BigInteger("" + alph.size)).intValue
                                                index = index.add(java.math.BigInteger.ONE)
                                                alph(n)
                                            })
                                        .mkString
                                }
                        }
                // Locations
                    object Locations
                        {
                            private var __list =
                                List(
                                    new Location("P12", "jeroen@datakneder.nl", "\\w", "908345urijhfus9-p2h3f924jprgjfsdodhgshdhjkshdgdghjskgh", 40, "Aa1")
                                        .add(new Application(None, "Mail", "p12.nl/webmail"))
                                    )
                            def list() : List[Location] = __list
                            private var __items : List[String] = List()
                            private var __locations : List[Location] = List()
                            def stringList() : List[String] = 
                                {
                                    if (__locations != __list) 
                                        {
                                            __locations = __list
                                            __items = __locations.map(_.applications().map(_.name)).foldLeft(List[String]())(_ ++ _)
                                            //System.out.println("Items Updated. (%d, %d)".format(__locations.map(_.applications.size).foldLeft(0)(_ + _), __items.size))
                                        }
                                    //System.out.println("Items retrieved: %d.".format(__items.size))
                                    __items
                                }
                            def addLocation() : Unit = 
                                {
                                    val nl = new Location("", "", "\\w", "", 40, "Aa1")
                                    val dialog = Dialog()
                                    
                                    dialog
                                        .caption("New Location")
                                        .menu(
                                            MenuBar(
                                                {menuBar => 
                                                    menuBar.add(
                                                        Menu("<html>&#9776;</html>")
                                                            .add(
                                                                MenuItem("Add new application")
                                                                    .accelerator(CTRL + VK_Equals)
                                                                    .onClick({() => nl.newApplication()}) 
                                                                )
                                                        
                                                        )
                                                }))
                                        .keyAction(VK_Enter, 
                                            {() =>
                                                __list = __list :+ nl
                                                dialog.close()
                                            })
                                        .keyAction(VK_Escape, {() => dialog.close()})
                                        .add(
                                            TwoColumnPanel()
                                                .minimalWidth(200)
                                                .add("Name", 
                                                    TextField()
                                                        .content({() => nl.name})
                                                        .content.update({x => nl.name = x}))
                                                .add("User", TextField())
                                                .add("Alphabet Pattern", 
                                                    TextField()
                                                        .content({() => nl.alphabetPattern})
                                                        .content.update({x => nl.alphabetPattern = x})
                                                    )
                                                .add("", 
                                                    Label()
                                                        .caption({() => nl.alphabet()}))
                                                .add("Seed", TextField())
                                                .add("Applications",
                                                        ListBox()
                                                            .items({() => nl.applications().map(_.name)}))
                                            )
                                        .show()
                                }
                            def edit() : Unit = 
                                {
                                    val dialog = Dialog()
                                    dialog
                                        .caption("Edit Locations")
                                        .menu(
                                            MenuBar(
                                                {menuBar => 
                                                    menuBar.add(
                                                        Menu("<html>&#9776;</html>")
                                                            .add(
                                                                MenuItem("Add new location")
                                                                    .accelerator(CTRL + VK_Equals)
                                                                    .onClick({() => Locations.addLocation()}) 
                                                                )
                                                        
                                                        )
                                                }))
                                        .keyAction(VK_Enter, {() => dialog.close()})
                                        .keyAction(VK_Escape, {() => dialog.close()})
                                        .add(
                                                BorderPanel()
                                                    .west(
                                                        ListBox()
                                                            .items({() => Locations.list.map(_.name)})
                                                        )
                                            )
                                        .show()
                                        
                                }
                                
                        }
                def login(_x : String, _password : String) = 
                    {
                        val location = Locations.list().filter(_.applications.map(_.name).contains(_x))(0)
                        val application = location.applications.filter(_.name == _x)(0)
                        application.login(_password)
                    }
                    
                def start() : Unit = 
                    {
                        object Data
                            {
                                var application = Text("Application") 
                                var password = Password("Password")
                                def connect() = 
                                    {
                                        login(application(), password())
                                    }
                            }
                        UI.DefaultComponent
                            .add({_ => _ => 
                                {case (p : UI.iConstructionData, n : String) if p.name == "Password" => 
                                    PasswordField()
                                        .minimalWidth(200)
                                        .content.applyCast(p.read)
                                        .content.update(p.write)
                                }})
                        UI.DefaultComponent
                            .add({_ => _ => 
                                {case (p : UI.iConstructionData, n : String) if p.name == "application" => 
                                    ComboBox()
                                        .items({() => Locations.stringList()})
                                        .content.applyCast(p.read)
                                        .content.update(p.write)
                                        .selectFirstItem() 
                                }})
                        UI.DefaultMenu
                            .add({_ => _ => 
                                {case Data => 
                                    List(
                                        Menu("<html>&#9776;</html>")
                                            .mnemonic("M")
                                            .add(
                                                    MenuItem("Edit Locations")
                                                        .onClick({() => Locations.edit()})
                                                        .accelerator(VK_F2)
                                                )
                                            .add(
                                                    MenuItem("Settings")
                                                        .onClick({() => Settings.edit()})
                                                        .accelerator(VK_F12)
                                                )
                                        )
                                }})
                            
                        if (Data.edit()) Data.connect()
                    }
            }
    }