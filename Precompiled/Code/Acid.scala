package nl.datakneder.core.Acid
    {
        import nl.datakneder.core.Packages._
        
        object Framework
            extends Template_FrameworkPackage("Acid", 1486800568662L /*CompileDate*/)
                {
                    object Acid
                        extends Template_Object("Acid")
                            {
                                trait Interface
                                    {
                                        // Management
                                            def clear() : Unit
                                            def successfullLines() : List[Int]
                                            def successfullLines(_x : List[Int]) : Unit
                                            def successfull() : Int 
                                            def failed() : Int 
                                            def currentClass(_x : AnyRef) : Unit
                                        // Tests
                                            def apply(_f : => Boolean) : Unit
                                            def not(_f : => Boolean) : Unit
                                            def exception(_f : => Unit) : Unit
                                            def compare(_x : => Any, _y : => Any) : Unit
                                            def string(_x : => Any, _y : => Any) : Unit
                                    }
                                def clear() : Unit = interface().clear()
                                def failed() : Int = interface().failed()
                                def apply(_f : => Boolean) : Unit = interface()(_f)
                                def apply(_x : => Any, _y : => Any) : Unit = interface().compare(_x, _y)
                                def not(_f : => Boolean) : Unit = interface().not(_f)
                                def exception(_f : => Unit) : Unit = interface().exception(_f)
                                def string(_x : => Any, _y : => Any) : Unit = interface().string(_x, _y)
                            }
                    object TestSet
                        {
                            trait Interface
                                {
                                    def name() : String
                                    def initialLineNumber : Int
                                    def run() : Unit
                                }
                            abstract class Class()
                                extends Interface
                                    {
                                        
                                    }
                        }
                }
    }
package nl.datakneder.core.Acid
    {
        import nl.datakneder.core.Packages._
        
        object Implementation
            extends Template_ImplementationPackage("Acid", 1486800568662L /*CompileDate*/, nl.datakneder.core.Acid.Framework)
                {
                    private val __fileName = "Check.ini"
                    // Utils
                        def getClassName(_x : AnyRef) : String = if (_x == null) "null" else _x.getClass.getName
                        object FileName
                            {
                                def apply(_x : AnyRef) : String = 
                                    {
                                        val className = getClassName(_x)
                                        var result = "No filename found for class '%s'." format className
                                        new Exception()
                                            .getStackTrace()
                                            .filter({t => className == t.getClassName})
                                            .map({t => t.getFileName})
                                            .take(1)
                                            .foreach({t => result = t})
                                        result    
                                    }
                            }
                        object LineNumber
                            {
                                def apply(_x : AnyRef) : Int = 
                                    {
                                        var result = -1
                                        val className = getClassName(_x)
                                        new Exception()
                                            .getStackTrace()
                                            .filter({t => className == t.getClassName})
                                            .map({t => t.getLineNumber})
                                            .take(1)
                                            .foreach({t => result = t})
                                        result    
                                    }
                            }

                    object TestSet
                        {
                            abstract class Class(_name : String)
                                extends Framework.TestSet.Interface
                                    {
                                        def name() : String = _name
                                        val initialLineNumber : Int = LineNumber(this)
                                    }
                        }
                    object Acid
                        extends Framework.Acid.Interface
                            {
                                // Management
                                    private var __successfullLines : List[Int] = List()
                                    private var __currentClass : AnyRef = this
                                    def currentClass(_x : AnyRef) : Unit = __currentClass = _x
                                    def successfullLines() : List[Int] = __successfullLines
                                    def successfullLines(_x : List[Int]) : Unit = __successfullLines = _x
                                    private var __successfull : Int = 0
                                    def successfull() : Int = __successfull 
                                    private var __failed : Int = 0
                                    def failed() : Int = __failed
                                    def clear() : Unit = 
                                        {
                                            __successfull = 0
                                            __failed = 0
                                            __successfullLines = List()
                                        }
                                    def success() : Unit = 
                                        {
                                            __successfullLines = __successfullLines :+ LineNumber(__currentClass)
                                            __successfull += 1
                                        }
                                    def fail() : Unit = 
                                        {
                                            System.out.println("%-5d Failed in (%s)!" format (LineNumber(__currentClass), FileName(__currentClass)))
                                            __failed += 1
                                        }
                                    def fail(_expected : String, _found : String) : Unit = 
                                        {
                                            System.out.println("%-5d Failed in (%s)!" format (LineNumber(__currentClass), FileName(__currentClass)))
                                            System.out.println("      Expected: " + _expected)
                                            System.out.println("      Found:    " + _found)
                                            __failed += 1
                                        }
                                //Tests
                                    def shouldApply(_f : => Unit) : Unit = if (!__successfullLines.contains(LineNumber(__currentClass))) _f
                                    def apply(_f : => Boolean) : Unit = shouldApply({if (_f) success() else fail()})
                                    def not(_f : => Boolean) : Unit = shouldApply({if (_f) fail() else success()})
                                    def exception(_f : => Unit) : Unit =
                                        shouldApply(
                                            {
                                                try 
                                                    {
                                                        _f
                                                        fail()
                                                    } 
                                                catch 
                                                    {
                                                        case _ : Throwable =>
                                                            success()
                                                    }
                                            })
                                    def compare(_x : => Any, _y : => Any) : Unit = 
                                        shouldApply(
                                            {
                                                val x = _x
                                                val y = _y
                                                if (x == y) success() else fail(if (x == null) "null" else x.toString, if (y == null) "null" else y.toString)
                                            })
                                    def string(_x : => Any, _y : => Any) : Unit = 
                                        shouldApply({compare(_x.toString, _y.toString)})
                            }
                            
                    abstract class Template_Acid(_name : String)
                        extends Template_Main(_name)
                            {
                                override def beforeRun() : Unit = 
                                    {
                                        super.beforeRun()
                                        argument("clear|c")
                                            .foreach(
                                                {_ => 
                                                    try 
                                                        {
                                                            new java.io.File(__fileName).delete()
                                                            System.out.println("Acid cleared.")
                                                        } 
                                                    catch 
                                                        {
                                                            case _ : Throwable =>
                                                                System.out.println("Error: Problem clearing Acid.")
                                                        }
                                                })
                                    }
                                def includeTests() : List[Framework.TestSet.Interface]
                                def run()
                                    {
                                        //import nl.datakneder.Acid.Framework._
                                        import nl.datakneder.core.Acid.Framework.{Acid => acid}
                                        
                                        // Implementation
                                            nl.datakneder.core.Acid.Implementation.initialise 
            
                                        // Initialising
                                            var total = 0
                                            var failed = 0
                                            var testResults : List[String] = List()
                                            // Load data
                                                var passes : List[String] = List()
                                                try 
                                                    {
                                                        val source = scala.io.Source.fromFile(__fileName)
                                                        try 
                                                            {
                                                                passes = source.mkString.split("\r\n").toList
                                                            } 
                                                        catch 
                                                            {
                                                                case _ : Throwable =>
                                                            }
                                                        finally source.close
                                                            
                                                    } 
                                                catch 
                                                    {
                                                        case _ : Throwable =>
                                                    }
                                                
                                            
                                        // Run
                                            System.out.println("Testing")
                                            includeTests()
                                                .foreach(
                                                    {test => 
                                                        Acid.clear()
                                                        Acid.currentClass(test)
                                                        passes
                                                            .filter({l => l.startsWith(getClassName(test) + ":")})
                                                            .foreach(
                                                                {l => 
                                                                    try 
                                                                        {
                                                                            Acid.successfullLines(l.split(":")(1).split(",").map(_.toInt + test.initialLineNumber).toList)    
                                                                        } 
                                                                    catch 
                                                                        {
                                                                            case _ : Throwable =>
                                                                        }
                                                                })
                                                        System.out.println("    " + test.name())
                                                        test.run()
                                                        testResults = testResults :+ (getClassName(test) + ":" + Acid.successfullLines().map({i => i - test.initialLineNumber}).mkString(","))
                                                        total += Acid.failed() + Acid.successfull()
                                                        failed += Acid.failed()
                                                    })
                                            System.out.println("(%d/%d) errors." format(failed, total))
                                        
                                        // Save data
                                            try 
                                                {
                                                    val p = new java.io.PrintWriter(new java.io.File(__fileName))
                                                    testResults.foreach(p.println(_))
                                                    p.close()
                                                } 
                                            catch 
                                                {
                                                    case _ : Throwable =>
                                                }
                                    }
                            }
                    def initialise() = 
                        {
                            Framework.Acid.assign(Implementation.Acid)
                        }
                }
    }
package nl.datakneder.core.Acid
    {
        import nl.datakneder.core.Packages._
        
        object Test
            extends Template_TestPackage("Acid", 1486800568662L /*CompileDate*/, nl.datakneder.core.Acid.Framework)
                {
                    class Acid1(_x : Framework.Acid.Interface)
                        extends Implementation.TestSet.Class("Acid 1")
                            {
                                import nl.datakneder.core.Acid.Framework.Acid
                                
                                def run() : Unit = 
                                    {
                                        // Exceptions
                                            Acid.exception({throw new Exception("")})
                                            //Acid.exception({})
                                            Acid.exception({throw new Exception("")})
                                            //Acid.exception({})
                                            //Acid.exception({})
                                            Acid.exception({throw new Exception("")})
                                            //Acid.exception({})
                                            //Acid.exception({})
                                            //Acid.exception({})
                                    }
                            }
                    class Acid2(_x : Framework.Acid.Interface)
                        extends Implementation.TestSet.Class("Acid 2")
                            {
                                import nl.datakneder.core.Acid.Framework.Acid
                                
                                def run() : Unit = 
                                    {
                                        // Not
                                            Acid.not(false)
                                            //Acid.not(true)
                                            Acid.not(
                                                {
                                                    val p = 3
                                                    val q = 4
                                                    q < p
                                                })
                                    }
                            }
                    class Acid3(_x : Framework.Acid.Interface)
                        extends Implementation.TestSet.Class("Acid 3")
                            {
                                import nl.datakneder.core.Acid.Framework.Acid
                                
                                def run() : Unit = 
                                    {
                                        // Compare
                                            Acid("A","A")
                                            Acid("ABC","A" + "B" + "C")
                                            //Acid("A","B")
                                            //Acid("A", null)
                                            //Acid(null, "A")
                                            Acid(null, null)
                                    }
                            }
                    class Acid4(_x : Framework.Acid.Interface)
                        extends Implementation.TestSet.Class("Acid 4")
                            {
                                import nl.datakneder.core.Acid.Framework.Acid
                                
                                def run() : Unit = 
                                    {
                                        // Strings
                                            Acid.string(<p>Henk</p>, <p>{List(<p>He</p>.child(0), <p>nk</p>.child(0))}</p>)
                                    }
                            }
                }
    }
    