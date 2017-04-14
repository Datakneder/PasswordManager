package nl.datakneder.core
    {
        object Packages
            {
                // Utils
                    // DeltaTime
                        private object DeltaTime
                            {
                                val Nato =        
                                    List(
                                        "Alpha",
                                        "Bravo",
                                        "Charlie",
                                        "Delta",
                                        "Echo",
                                        "Foxtrot",
                                        "Golf",
                                        "Hotel",
                                        "India",
                                        "Juliett",
                                        "Kilo",
                                        "Lima",
                                        "Mike",
                                        "November",
                                        "Oscar",
                                        "Papa",
                                        "Quebec",
                                        "Romeo",
                                        "Sierra",
                                        "Tango",
                                        "Uniform",
                                        "Victor",
                                        "Whiskey",
                                        "Xray",
                                        "Yankee",
                                        "Zulu")
                                    
                                def indexes(_x : Long) : Array[Int] =
                                    {
                                        val size = Nato.size
                                        var n = slotsFromStart(_x)
                                        Range(0,5,1)
                                            .map(
                                                {_ => 
                                                    val result = (n % size).toInt
                                                    n = n / size
                                                    result
                                                })
                                            .toArray.reverse
                                    }
                                            
                                def slotsFromStart(_x : Long) : Long = 
                                    {
                                        val d = new java.util.GregorianCalendar(1970,0,1)
                                        d.add(java.util.Calendar.SECOND,(_x / 1000).toInt)
                                        (d.getTimeInMillis - new java.util.GregorianCalendar(d.get(java.util.Calendar.YEAR),0,1).getTimeInMillis) / 3000 // Split into slots of 3 sec.
                                    }
                                def year() : Int = year(System.currentTimeMillis)
                                def year(_x : Long) : Int = 
                                    {
                                        val d = new java.util.GregorianCalendar(1970,0,1)
                                        d.add(java.util.Calendar.SECOND,(_x / 1000).toInt)
                                        d.get(java.util.Calendar.YEAR)
                                    }
                                def major() : String = major(System.currentTimeMillis)
                                def major(_x : Long) : String = Nato(indexes(_x)(0))
                                def minor() : String = minor(System.currentTimeMillis)
                                def minor(_x : Long) : String = Nato(indexes(_x)(1))
                                def build() : String = build(System.currentTimeMillis)
                                def build(_x : Long) : String = Nato(indexes(_x)(2)) + "." + Nato(indexes(_x)(3)) + "." + Nato(indexes(_x)(4))
                                def version() : String = version(System.currentTimeMillis)
                                def version(_x : Long) : String = List(year(_x), major(_x), minor(_x)).mkString(".") 
                            }
                    // Packages
                        object Packages
                            {
                                private var __list : List[Template_Package] = List()
                                def add(_x : Template_Package) : Unit = 
                                    {
                                        //System.out.println(_x.Package.name() + " (" + _x.Package.kind() + ") " + _x.Package.version())
                                        __list = __list :+ _x
                                    }
                                def apply() : List[Template_Package] = __list
                                def implementations() : List[Template_ImplementationPackage] = 
                                    __list
                                        .filter({p => p.isInstanceOf[Template_ImplementationPackage]})
                                        .map({p => p.asInstanceOf[Template_ImplementationPackage]})
                            }
                
                // Templates
                    import scala.language.higherKinds
                    // Object Template
                        abstract class Template_Object(_name : String)
                            {
                                type Interface
                                
                                protected var __source : Option[Interface] = None
                                def assign(_x : Interface) : this.type = 
                                    {
                                        __source = Some(_x)
                                        this
                                    }
                                def interface() : Interface =
                                    {
                                        if (__source == None) throw new Exception(_name + " not initialised.")
                                        __source.get
                                    }
                            }
                    // Factories
                        // Abstract Template
                            abstract class Template_Factory(_name : String)
                                {
                                    type iFactory
                                    private var __factory : Option[iFactory] = None
                                    def factory(_x : iFactory) : Unit = __factory = Some(_x)
                                    
                                    protected def factory() : iFactory = 
                                        {
                                            if (__factory == None) throw new Exception(_name + " not initialised.")
                                            __factory.get
                                        }
                                }
                    // Packages        
                        abstract class Template_Package(_name : String, _stamp : Long)
                            {
                                def Package : AbstractPackage 
                                abstract class AbstractPackage(_name : String, _stamp : Long, _kind : String)
                                    {
                                        def name() : String = _name
                                        def kind() : String = _kind
                                        def version() : String = DeltaTime.version(_stamp)
                                        def major() : String = DeltaTime.major(_stamp)
                                    }
                                Packages.add(this)
                            }
                        abstract class Template_FrameworkPackage(_name : String, _stamp : Long)
                            extends Template_Package(_name, _stamp)
                                {
                                    object Package
                                        extends AbstractPackage(_name, _stamp, "Framework")
                                            {
                                                
                                            }
                                }
                        abstract class Template_ImplementationPackage(_name : String, _stamp : Long, _framework : Template_FrameworkPackage)
                            extends Template_Package(_name, _stamp)
                                {
                                    object Package
                                        extends AbstractPackage(_name, _stamp, "Implementation")
                                            {
                                                
                                            }
                                    def initialise() : Unit
                                }
                        abstract class Template_TestPackage(_name : String, _stamp : Long, _framework : Template_FrameworkPackage)
                            extends Template_Package(_name, _stamp)
                                {
                                    object Package
                                        extends AbstractPackage(_name, _stamp, "Test")
                                            {
                                                
                                            }
                                    
                                }

                        
                        
                    // Executable
                        abstract class Template_Main(_name : String)
                            {
                                private var __arguments : List[String] = List()
                                private def argumentIndex(_p : String) : Option[Int] = 
                                    {
                                        val pattern = java.util.regex.Pattern.compile(_p)
                                        val it = 
                                            Range(0, __arguments.size, 1)
                                                .iterator
                                                .filter(
                                                    {i => 
                                                        pattern.matcher(__arguments(i)).matches()
                                                    })
                                        if (it.hasNext) Some(it.next) else None
                                    }
                                def argument(_i : Int) : Option[String] = if (0 <= _i && _i < __arguments.size) Some(__arguments(_i)) else None
                                def argumentCount() : Int = __arguments.size
                                def argument(_p : String) : Option[String] = argument(_p, 0)
                                def argument(_p : String, _offset : Int) : Option[String] = argumentIndex(_p).map({i => __arguments(i + _offset)})
                                def run() : Unit
                                def beforeRun() : Unit = {}
                                def main(args : Array[String])
                                    {
                                        __arguments = args.toList
                                        beforeRun()
                                        run()
                                    }

                            }
            }
    }