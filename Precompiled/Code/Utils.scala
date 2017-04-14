package nl.datakneder.core.Utils
    {
        import scala.language.implicitConversions
        import scala.language.reflectiveCalls
        
        import nl.datakneder.core.Packages._
        
        object Framework
            extends Template_FrameworkPackage("Utils", 1490176360392L /*CompileDate*/)
                {
                    // Reflect
                        object Reflect
                            {
                                def apply[A](_f : => Unit, _r : A) : A = 
                                    {
                                        _f
                                        _r
                                    }
                            }
                    // Range
                        object Range
                            extends Template_Object("Range")
                                {
                                    trait Interface
                                        {
                                            def infinite(_x : Int) : Iterator[Int]  
                                            def infinite : Iterator[Int]  
                                            def apply(_x : Int) : Iterator[Int]
                                            def apply(_x : Iterable[_]) : Iterator[Int]
                                            def apply(_x : Int, _y : Int) : Iterator[Int]
                                            def apply(_x : Int, _y : Int, _z : Int) : Iterator[Int]
                                        }
                                    def infinite(_x : Int) : Iterator[Int] = interface().infinite(_x) 
                                    def infinite : Iterator[Int] = interface().infinite
                                    def apply(_x : Int) : Iterator[Int] = interface()(_x)
                                    def apply(_x : Iterable[_]) : Iterator[Int] = interface()(_x)
                                    def apply(_x : Int, _y : Int) : Iterator[Int] = interface()(_x, _y)
                                    def apply(_x : Int, _y : Int, _z : Int) : Iterator[Int] = interface()(_x, _y, _z)
                                }
                    // Cast
                        object Cast
                            extends Template_Object("Cast")
                                {
                                    trait Interface
                                        {
                                            def apply[A](_x : Any, _f : PartialFunction[Any, A]) : Option[A]
                                            def iterator[A](_x : Iterator[Any], _f : PartialFunction[Any, A]) : Iterator[A]
                                            def iterable[A](_x : Iterable[Any], _f : PartialFunction[Any, A]) : Iterator[A]
                                        }
                                    def apply[A](_x : Any, _f : PartialFunction[Any, A]) : Option[A] = interface()(_x, _f)
                                    def iterator[A](_x : Iterator[Any], _f : PartialFunction[Any, A]) : Iterator[A] = interface().iterator(_x, _f)
                                    def iterable[A](_x : Iterable[Any], _f : PartialFunction[Any, A]) : Iterator[A] = interface().iterable(_x, _f)
                                }
                        implicit def CastIterator[A](_x : Iterator[Any]) =
                            new Object
                                {
                                    def cast(_f : PartialFunction[Any, A]) : Iterator[A] = Cast.iterator(_x, _f)
                                }
                        implicit def CastIterator[A](_x : Iterable[Any]) =
                            new Object
                                {
                                    def cast(_f : PartialFunction[Any, A]) : Iterator[A] = Cast.iterable(_x, _f)
                                }
                    // ClipBoard
                        object ClipBoard
                            extends Template_Object("ClipBoard")
                                {
                                    trait Interface
                                        {
                                            def copyTo(_x : String) : this.type
                                            def content() : String
                                            def apply(_x : String) : this.type
                                            def apply() : String
                                        }
                                    def copyTo(_x : String) : this.type = Reflect(interface().copyTo(_x), this)
                                    def content() : String = interface().content()
                                    def apply(_x : String) : this.type = Reflect(interface()(_x), this)
                                    def apply() : String = interface()()
                                }
                    // Sort
                        object Sort
                            extends Template_Object("Sort")
                                {
                                    trait Interface
                                        {
                                            def apply[A <: Comparable[A]](_x : Array[A])(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A]    
                                            def apply(_x : Array[Int]) : Array[Int] 
                                            def unique[A <: Comparable[A]](_x : Array[A])(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A]  
                                            def unique(_x : Array[Int]) : Array[Int] 
                                            def apply[A](_x : Array[A], _f : (A,A) => Boolean)(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A]  
                                        }                            
                                    def apply[A <: Comparable[A]](_x : Array[A])(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A] = interface()(_x)
                                    def apply(_x : Array[Int]) : Array[Int]  = interface()(_x)
                                    def unique[A <: Comparable[A]](_x : Array[A])(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A] = interface()(_x)  
                                    def unique(_x : Array[Int]) : Array[Int]  = interface()(_x)
                                    def apply[A](_x : Array[A], _f : (A,A) => Boolean)(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A] = interface()(_x, _f)  
                                }
                    // Loop
                        object Loop
                            {   
                                def apply(_f : => Boolean) = while (!_f) {}
                            }
                    // String Manipulation
                        object Cut
                            extends Template_Object("Cut")
                                {
                                    trait Interface
                                        {
                                            def index(_x : String, _y : String, _i : Int) : Int 
                                            def from(_x : String, _y : String, _i : Int) : String   
                                            def to(_x : String, _y : String, _i : Int) : String 
                                        }
                                    def index(_x : String, _y : String, _i : Int) : Int = interface().index(_x, _y, _i)
                                    def from(_x : String, _y : String, _i : Int) : String =  interface().from(_x, _y, _i)
                                    def to(_x : String, _y : String, _i : Int) : String =  interface().to(_x, _y, _i)
                                }
                        
                        implicit def implicitString(_x : String) = 
                            new Object
                                {
                                    def cutFrom(_y : String) : String = cutFrom(_y, 1)
                                    def cutFrom(_y : String, _i : Int) : String = Cut.from(_x, _y, _i)
                                    def cutTo(_y : String) : String = cutTo(_y, 1)
                                    def cutTo(_y : String, _i : Int) : String = Cut.to(_x, _y, _i)
                                    def mod(_i : Int) : Char = IteratorFunctions(_x.iterator).mod(_i)
                                }
                    // TryCatch
                        object TryCatch
                            extends Template_Object("TryCatch")
                                {
                                    trait Interface
                                        {
                                            def apply[A](_t : => A, _c : => A) : A  
                                            def apply[A](_t : => A, _c : Throwable => A) : A 
                                        }
                                        
                                    def apply[A](_t : => A, _c : => A) : A = interface()(_t, _c)
                                    def apply[A](_t : => A, _c : Throwable => A) : A = interface()(_t, _c)
                                }
                        object Try
                            {
                                def apply(_t : => Unit) = TryCatch(_t, {})
                            }
                    // Iterator 
                        object Iterate
                            extends Template_Object("Iterate")
                                {
                                    trait Interface
                                        {
                                            def option[A](_initial : A, _next : A => Option[A]) : Iterator[A]
                                            def option[A](_initial : Option[A], _next : A => Option[A]) : Iterator[A]
                                            def apply[A](_initial : A, _next : A => A) : Iterator[A]
                                        }
                                    //class Class 
                                    //    extends Interface[A]
                                    //        {
                                    //            def option[A](_initial : A, _next : A => Option[A]) : Iterator[A] = option(Some(_initial), _next)
                                    //            def option[A](_initial : Option[A], _next : A => Option[A]) : Iterator[A] =
                                    //                new Iterator[A]
                                    //                    {
                                    //                        private var __current : Option[A] = _initial
                                    //                        def hasNext() : Boolean = __current != None
                                    //                        def next() : A =
                                    //                            {
                                    //                                val result = __current.get
                                    //                                __current = _next(__current.get)
                                    //                                result
                                    //                            }
                                    //                    }
                                    //            def apply[A](_initial : A, _next : A => A) : Iterator[A] = 
                                    //                option(if (_initial == null) None else Some(_initial), 
                                    //                    {(x : A) =>
                                    //                        val n = _next(x)
                                    //                        if (n == null) None else Some(x)
                                    //                    })
                                    //        }
                                    def option[A](_initial : A, _next : A => Option[A]) : Iterator[A] = interface().option[A](_initial, _next)
                                    def option[A](_initial : Option[A], _next : A => Option[A]) : Iterator[A] = interface().option[A](_initial, _next)
                                    def apply[A](_initial : A, _next : A => A) : Iterator[A] = interface().apply[A](_initial, _next)

                                }
                                
                        implicit def IteratorFunctions[A](_x : Iterator[A]) = 
                            new Object
                                {
                                    def nextOrNone() : Option[A] = if (_x.hasNext) Some(_x.next) else None
                                    def nextOrElse(_f : => A) : A = if (_x.hasNext) _x.next else _f
                                    def mod(_i : Int) : A = 
                                        {
                                            val l = _x.toList
                                            l(((_i % l.size) + l.size) % l.size)
                                        }
                                }
                        implicit def IterableFunctions[A](_x : Iterable[A]) = 
                            new Object
                                {
                                    def nextOrNone() : Option[A] = if (_x.iterator.hasNext) Some(_x.iterator.next) else None
                                    def nextOrElse(_f : => A) : A = if (_x.iterator.hasNext) _x.iterator.next else _f
                                    def mod(_i : Int) : A = 
                                        {
                                            val l = _x.toList
                                            l(((_i % l.size) + l.size) % l.size)
                                        }
                                }
                    
                    // Lists
                        object Lists
                            extends Template_Object("Lists")
                                {
                                    trait Interface
                                        {
                                            def move[A](_from : Int, _to : Int, _l : List[A]) : List[A]   
                                            def swap[A](_from : Int, _to : Int, _l : List[A]) : List[A]   
                                        }
                                    def move[A](_from : Int, _to : Int, _l : List[A]) : List[A] = interface().move(_from, _to, _l)
                                    def swap[A](_from : Int, _to : Int, _l : List[A]) : List[A] = interface().swap(_from, _to, _l)
                                }
                        implicit def ListFunctions[A](_x : List[A]) = 
                            new Object
                                {
                                    def move(_from : Int, _to : Int) : List[A] = Lists.move(_from, _to, _x)
                                    def swap(_from : Int, _to : Int) : List[A] = Lists.swap(_from, _to, _x)
                                }
                    // Context
                        object Context
                            extends Template_Factory("Context")
                                {
                                    trait Interface[B]
                                        {
                                            def apply(_root : Interface[B], _x : Any) : B
                                            def apply(_x : Any) : B
                                            def add(_f : Interface[B] => Interface[B] => PartialFunction[Any, B]) : Interface[B]
                                            def copy() : Interface[B]
                                        }
                                    trait iFactory
                                        {
                                            def apply[B](_f : Any => B) : Interface[B]
                                        }
    
                                    def apply[B](_f : Any => B) : Interface[B] = factory()(_f)
                                }
                    // FileHandler
                        object FileHandler
                            extends Template_Object("FileHandler")
                                {
                                    trait Interface
                                        {
                                            def correct(_x : String) : String
                                            def create(_f : String) : Unit
                                            def makeFolder(_x : String) : String 
                                            def getFolder(_x : String) : String
                                            def getFileName(_x : String) : String
                                            def isFile(_x : String) : Boolean 
                                            def isFolder(_x : String) : Boolean 
                                            def isValid(_x : String) : Boolean 
                                            def isFullName(_x : String) : Boolean 
                                            def isExisting(_x : String) : Boolean 
                                            def isRelative(_x : String) : Boolean 
                                            def shouldBeValid(_x : String) : Unit
                                            def shouldBeFile(_x : String) : Unit
                                            def shouldBeFullName(_x : String) : Unit 
                                            def shouldExist(_x : String) : Unit 
                                            def shouldBeRelative(_x : String) : Unit 
                                            def shouldBeFolder(_x : String) : Unit 
                                            
                                            def copyFile(_from : String, _to : String) : Unit
                                            def copyFolder(_from : String, _to : String) : Unit
                                            
                                            def deleteFile(_x : String) : Unit
                                            def deleteFolder(_x : String) : Unit
                                            
                                            def xml(_x : scala.xml.Node, _f : String) : Unit
                                            def xml(_f : String) : Option[scala.xml.Node]
                                            
                                            def save(_f : String, _x : String) : Unit
                                            def load(_f : String) : String
                                            
                                            def drive(_f : String) : Option[String]
                                            def parentFolder(_f : String) : Option[String]
                                            
                                            def relativeTo(_original : String, _r : String) : String
                                            
                                            def join(_x : String, _y : String) : String
                                            def rename(_x : String, _y : String) : String
                                            def extension(_x : String) : String
                                            def files(_x : String) : List[String]
                                            def folders(_x : String) : List[String]
                                            def lastModified(_x : String) : Long 
                                            def renameOnDrive(_x : String, _y : String) : String
                                        }
                                    
                                    def correct(_x : String) : String = interface().correct(_x)
                                    def create(_f : String) : Unit = interface().create(_f)
                                    def makeFolder(_x : String) : String = interface().makeFolder(_x)
                                    def getFolder(_x : String) : String = interface().getFolder(_x)
                                    def getFileName(_x : String) : String = interface()getFileName(_x)
                                    def isFile(_x : String) : Boolean  = interface().isFile(_x)
                                    def isFolder(_x : String) : Boolean  = interface().isFolder(_x)
                                    def isValid(_x : String) : Boolean  = interface().isValid(_x)
                                    def isFullName(_x : String) : Boolean  = interface().isFullName(_x)
                                    def isExisting(_x : String) : Boolean  = interface().isExisting(_x)
                                    def isRelative(_x : String) : Boolean  = interface().isRelative(_x)
                                    def shouldBeValid(_x : String) : Unit = interface().shouldBeValid(_x)
                                    def shouldBeFile(_x : String) : Unit = interface().shouldBeFile(_x)
                                    def shouldBeFullName(_x : String) : Unit  = interface().shouldBeFullName(_x)
                                    def shouldExist(_x : String) : Unit  = interface().shouldExist(_x)
                                    def shouldBeRelative(_x : String) : Unit  = interface().shouldBeRelative(_x)
                                    def shouldBeFolder(_x : String) : Unit  = interface().shouldBeFolder(_x)
                                    def copyFile(_from : String, _to : String) : Unit = interface().copyFile(_from, _to)
                                    def copyFolder(_from : String, _to : String) : Unit = interface().copyFolder(_from, _to)
                                    def deleteFile(_x : String) : Unit = interface().deleteFile(_x)
                                    def deleteFolder(_x : String) : Unit = interface().deleteFolder(_x)
                                    def xml(_x : scala.xml.Node, _f : String) : Unit = interface().xml(_x, _f)
                                    def xml(_f : String) : Option[scala.xml.Node] = interface().xml(_f)
                                    def save(_f : String, _x : String) : Unit = interface().save(_f, _x)
                                    def load(_f : String) : String = interface().load(_f)
                                    def drive(_f : String) : Option[String] = interface().drive(_f)
                                    def parentFolder(_f : String) : Option[String] = interface().parentFolder(_f)
                                    def relativeTo(_original : String, _r : String) : String = interface().relativeTo(_original, _r)
                                    def join(_x : String, _y : String) : String = interface().join(_x, _y)
                                    def rename(_x : String, _y : String) : String = interface().rename(_x, _y)
                                    def extension(_x : String) : String = interface().extension(_x)
                                    def files(_x : String) : List[String] = interface().files(_x)
                                    def folders(_x : String) : List[String] = interface().folders(_x)
                                    def lastModified(_x : String) : Long  = interface().lastModified(_x)
                                    def renameOnDrive(_x : String, _y : String) : String = interface().renameOnDrive(_x, _y)
                                }
                    // Validator
                        object Validator
                            extends Template_Factory("Validator")
                                {
                                    trait Interface[A]
                                        {
                                            def add(_n : String, _x : A => Boolean, _m : A => String) : Interface[A]
                                            def isValid(_x : A) : Boolean
                                            def ifValid(_x : A, _f : A => Unit) : this.type
                                            def ifNotValid(_x : A, _f : A => Unit) : this.type
                                            def message(_x : A) : Option[String]
                                        }
                                    trait iFactory
                                        {
                                            def apply[A]() : Interface[A]
                                        }
                                    def apply[A]() : Interface[A] = factory()[A]()
                                    def apply[A](_n : String, _x : A => Boolean, _m : A => String) : Interface[A] = apply[A]().add(_n, _x, _m)
                                }
                    // Sleep
                        object Sleep
                            extends Template_Object("Sleep")
                                {
                                    trait Interface
                                        {
                                            def apply(_x : Long) : Unit  
                                        }
                                    def apply(_x : Long) : Unit = interface().apply(_x)
                                }
                }
    }
package nl.datakneder.core.Utils
    {
        import nl.datakneder.core.Packages._
        
        object Implementation
            extends Template_ImplementationPackage("Utils", 1490176360392L /*CompileDate*/, nl.datakneder.core.Utils.Framework)
                {
                    // Range
                        object Range
                            extends Framework.Range.Interface
                                {
                                    def infinite(_x : Int) : Iterator[Int] = 
                                        new Iterator[Int]
                                            {
                                                var current = _x - 1
                                                def hasNext = true
                                                def next = 
                                                    {
                                                        current += 1
                                                        current
                                                    }
                                            }
                                    def infinite : Iterator[Int] = infinite(0) 
                                    def apply(_x : Int) : Iterator[Int] = new Range(0, _x, 1).iterator
                                    def apply(_x : Iterable[_]) : Iterator[Int] = apply(_x.size)
                                    def apply(_x : Int, _y : Int) : Iterator[Int] = new Range(_x, _y, 1).iterator
                                    def apply(_x : Int, _y : Int, _z : Int) : Iterator[Int] = new Range(_x, _y, _z).iterator
                                }
                    // Cast
                        object Cast
                            extends Framework.Cast.Interface
                                {
                                    def apply[A](_x : Any, _f : PartialFunction[Any, A]) : Option[A] = if (_f.isDefinedAt(_x)) Some(_f(_x)) else None
                                    def iterator[A](_x : Iterator[Any], _f : PartialFunction[Any, A]) : Iterator[A] = _x.filter(_f.isDefinedAt(_)).map(_f(_))
                                    def iterable[A](_x : Iterable[Any], _f : PartialFunction[Any, A]) : Iterator[A] = iterator(_x.iterator, _f)
                                }
                    // ClipBoard
                        object ClipBoard
                            extends Framework.ClipBoard.Interface
                                {
                                    val __clipBoard = java.awt.Toolkit.getDefaultToolkit.getSystemClipboard
        
                                    def copyTo(_x : String) : this.type =
                                            {
                                                __clipBoard.setContents(new java.awt.datatransfer.StringSelection(_x), null)
                                                this
                                            }
                                    def content() : String = 
                                        {
                                            __clipBoard.getContents(__clipBoard).getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor).toString
                                        }
                                    def apply(_x : String) : this.type = copyTo(_x)
                                    def apply() : String = content()
                                }
                    // Sort
                        object Sort
                            extends Framework.Sort.Interface
                                {
                                    def apply[A <: Comparable[A]](_x : Array[A])(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A] =   
                                        {
                                            val result = _x.iterator.toArray
                                            scala.util.Sorting.stableSort(result)
                                            result
                                        }
                                    def apply(_x : Array[Int]) : Array[Int] = 
                                        {
                                            val result = _x.iterator.toArray
                                            scala.util.Sorting.stableSort(result)
                                            result
                                        }
                                    def unique[A <: Comparable[A]](_x : Array[A])(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A] = 
                                        {
                                            val r : Array[A] = apply(_x)
                                            if (r.size == 0) 
                                                {
                                                    r
                                                } 
                                            else 
                                                {
                                                    Array[A](r(0)) ++ Range(1,r.size,1).filter({i => r(i-1).compareTo(r(i)) < 0}).map({i => r(i)}).toArray[A]
                                                }
                                        }
                                    def unique(_x : Array[Int]) : Array[Int] =
                                        {
                                            val r : Array[Int] = apply(_x)
                                            if (r.size == 0) 
                                                {
                                                    r
                                                } 
                                            else 
                                                {
                                                    Array[Int](r(0)) ++ Range(1,r.size,1).filter({i => r(i-1) < r(i)}).map({i => r(i)}).toArray[Int]
                                                }
                                        }
        
                                    def apply[A](_x : Array[A], _f : (A,A) => Boolean)(implicit arg0 : scala.reflect.ClassTag[A]) : Array[A] = 
                                        {
                                            val result = _x.iterator.toArray
                                            scala.util.Sorting.stableSort(result, _f)
                                            result
                                        }
                                    
                                }
                    
                                
                    // String Manipulation
                        object Cut
                            extends Framework.Cut.Interface
                                {
                                    def index(_x : String, _y : String, _i : Int) : Int =
                                        {
                                            var result = if (_i >= 0) 0 else _x.size 
                                            var i = _i
                                            while (i != 0 && result != -1) 
                                                {
                                                    if (i > 0) 
                                                        {
                                                            result = _x.indexOf(_y, result)
                                                            i -= 1
                                                            if (result >= 0) result += _y.size
                                                        } 
                                                    else 
                                                        {
                                                            result = _x.substring(0, result).lastIndexOf(_y)
                                                            i += 1
                                                            if (result >= 0) result -= _y.size
                                                        }
                                                }
                                            result
                                        }
                                    def from(_x : String, _y : String, _i : Int) : String =  
                                        {
                                            index(_x, _y, _i) match
                                                {
                                                    case -1 => 
                                                        if (_i < 0) _x else ""
                                                    case i => if (_i < 0) _x.substring(i + 2 * _y.size) else _x.substring(i)
                                                }
                                        }
                                    def to(_x : String, _y : String, _i : Int) : String =  
                                        {
                                            index(_x, _y, _i) match
                                                {
                                                    case -1 => 
                                                        if (_i < 0) "" else _x
                                                    case i => if (_i < 0) _x.substring(0, i + _y.size) else _x.substring(0, Math.max(0,i - _y.size))
                                                }
                                            
                                        }
                                }
                    // TryCatch
                        object TryCatch
                            extends Framework.TryCatch.Interface
                                {
                                    def apply[A](_t : => A, _c : => A) : A = apply(_t, {_ => _c}) 
                                    def apply[A](_t : => A, _c : Throwable => A) : A = 
                                    try 
                                        {
                                            _t
                                        } 
                                    catch 
                                        {
                                            case x : Throwable =>
                                                _c(x)
                                        }
                                }
                    // Iterate
                        object Iterate
                            extends Framework.Iterate.Interface
                                {
                                    def option[A](_initial : A, _next : A => Option[A]) : Iterator[A] = option(Some(_initial), _next)
                                    def option[A](_initial : Option[A], _next : A => Option[A]) : Iterator[A] =
                                        new Iterator[A]
                                            {
                                                private var __current : Option[A] = _initial
                                                def hasNext() : Boolean = __current != None
                                                def next() : A =
                                                    {
                                                        val result = __current.get
                                                        __current = _next(__current.get)
                                                        result
                                                    }
                                            }
                                    def apply[A](_initial : A, _next : A => A) : Iterator[A] = 
                                        option(if (_initial == null) None else Some(_initial), 
                                            {(x : A) =>
                                                val n = _next(x)
                                                if (n == null) None else Some(x)
                                            })
                                }
                    // Lists
                        object Lists
                            extends Framework.Lists.Interface
                                {
                                    def move[A](_from : Int, _to : Int, _l : List[A]) : List[A] = 
                                        {
                                            val from = Math.max(0, Math.min(_from, _l.size - 1))
                                            val to = Math.max(0, Math.min(_to, _l.size - 1))
                                            
                                            if (from == to || from == _l.size || to == _l.size) return _l
                                            
                                            if (_from < _to) 
                                                {
                                                    _l.take(from) ++ _l.drop(from + 1).take(to - from) ++ List(_l(from)) ++ _l.drop(to + 1)
                                                } 
                                            else 
                                                {
                                                    _l.take(to) ++ List(_l(from)) ++ _l.drop(to).take(from - to) ++ _l.drop(from + 1)
                                                }
                                        }
                                    def swap[A](_from : Int, _to : Int, _l : List[A]) : List[A] = 
                                        {
                                            val from = Math.max(0, Math.min(_from, _l.size - 1))
                                            val to = Math.max(0, Math.min(_to, _l.size - 1))
                                            
                                            if (from == to || from == _l.size || to == _l.size) return _l
                                            
                                            if (_from < _to) 
                                                {
                                                    _l.take(from) ++ List(_l(to)) ++ _l.drop(from + 1).take(to - from - 1) ++ List(_l(from)) ++ _l.drop(to + 1)
                                                } 
                                            else 
                                                {
                                                    _l.take(to) ++ List(_l(from)) ++ _l.drop(to + 1).take(from - to - 1) ++ List(_l(to)) ++ _l.drop(from + 1)
                                                }
                                        }
                                }
                    // Context
                        object Context
                            extends Framework.Context.iFactory
                                {
                                    import Framework.Context._
                                    
                                    class Context[B](_function : Interface[B] => Interface[B] => PartialFunction[Any, B], _parent : Option[Interface[B]])
                                        extends Interface[B]
                                            {
                                                def apply(_root : Interface[B], _x : Any) : B = 
                                                {
                                                    val partial = _function(_root)(this)
                                                    if (partial.isDefinedAt(_x)) partial(_x) else _parent.get.apply(_root, _x)
                                                }
                                                def apply(_x : Any) : B = apply(this, _x)
                                                def add(_f : Interface[B] => Interface[B] => PartialFunction[Any, B]) : Interface[B] = new Context(_f, Some(this))
                                                def copy() : Interface[B] = new Context(_function, _parent)
                                            }
                                    def apply[B](_f : Any => B) : Interface[B] = new Context(_ => _ => {case x => _f(x)}, None) 
    
                                }
                    // FileHandler
                        object FileHandler
                            extends Framework.FileHandler.Interface
                                {
                                    private val separator = java.io.File.separator
                                    def correct(_x : String) : String = if (_x == null) "" else _x.replace("/", separator)
                                    def getFolder(_f : String) : String = 
                                        {
                                            if (isFolder(_f)) return _f 
                                            new java.io.File(_f).getParent match
                                                {
                                                    case null =>
                                                        ".\\"
                                                    case "" =>
                                                        ".\\"
                                                    case x =>
                                                        //System.out.println("X = " + x)
                                                        makeFolder(x)
                                                }
                                            
                                        }
                                    def getFileName(_x : String) : String = 
                                        {
                                            if (isFolder(_x)) throw new Exception("Trying to read a folder as a file")
                                            new java.io.File(_x).getName
                                        }
                                    def makeFolder(_x : String) : String = 
                                        {
                                            if (_x == "") return ".\\"
                                            val x = correct(_x)
                                            if (isFolder(x)) x else x + separator
                                        }
                                    def isFile(_x : String) : Boolean = 
                                        {
                                            val x = correct(_x)
                                            isValid(x) && !isFolder(x) && x.size > 0
                                        }
                                    def isFolder(_x : String) : Boolean = 
                                        {
                                            val x = correct(_x)
                                            isValid(x) && x.endsWith(separator)
                                        }
    
                                    def isValid(_x : String) : Boolean = 
                                        {
                                            try 
                                                {
                                                    val x = correct(_x)
                                                    new java.io.File(x).getCanonicalPath.size > 0 && x.size > 0
                                                } 
                                            catch 
                                                {
                                                    case _ : Throwable =>
                                                        false
                                                }
                                        }
                                    def isFullName(_x : String) : Boolean = 
                                        {
                                            val x = correct(_x)
                                            isValid(x) && new java.io.File(x).isAbsolute
                                        }
                                    def isExisting(_x : String) : Boolean =  
                                        {
                                            val x = correct(_x)
                                            new java.io.File(x).exists()
                                        }
                                    def isRelative(_x : String) : Boolean =
                                        {
                                            val x = correct(_x)
                                            isValid(x) && !isFullName(x)
                                        }
                                        
                                    def shouldBeValid(_x : String) : Unit = if (!isValid(_x)) throw new Exception("Not a valid folder or file '" + _x + "'")
                                    def shouldBeFile(_x : String) : Unit = if (isFolder(_x)) throw new Exception("Not a valid file '" + _x + "'")
                                    def shouldBeFolder(_x : String) : Unit = if (!isFolder(_x)) throw new Exception("Not a valid folder '" + _x + "'")
                                    def shouldBeFullName(_x : String) : Unit = if (!isFullName(_x)) throw new Exception("Not a full folder of file '" + _x + "'")
                                    def shouldExist(_x : String) : Unit = if (!isExisting(_x)) throw new Exception("Not an existing folder or file '" + _x + "'")
                                    def shouldBeRelative(_x : String) : Unit = {shouldBeValid(_x.toString)} // A full name could also be a relative file
                                    
                                    def create(_f : String) : Unit = 
                                        {
                                            shouldBeFolder(_f)
                                            new java.io.File(_f).mkdirs
                                        }
                                    def copyFile(_from : String, _to : String) = 
                                        {
                                            shouldBeFile(_from)
                                            shouldBeFile(_to)
                                            java.nio.file.Files.copy(new java.io.File(_from).toPath, new java.io.File(_to).toPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING)
                                        }
                                    def copyFolder(_from : String, _to : String) : Unit = 
                                        {
                                            shouldBeFolder(_from)
                                            shouldBeFolder(_to)
                                            new java.io.File(_to).mkdirs()
                                            new java.io.File(_from)
                                                .listFiles()
                                                .foreach(
                                                    {f =>
                                                        if (f.isDirectory)
                                                            {
                                                                copyFolder(makeFolder(f.toString),makeFolder(_to + f.getName) )
                                                            }
                                                            else
                                                                {
                                                                    copyFile(f.toString, _to + f.getName)
                                                                }
                                                    })
                                        }
                                    def deleteFile(_x : String) : Unit = 
                                        {
                                            shouldBeFile(_x)
                                            val x = correct(_x)
                                            new java.io.File(x).delete()
                                        }
                                    def deleteFolder(_x : String) : Unit = 
                                        {
                                            shouldBeFolder(_x)
                                            new java.io.File(_x)
                                                .listFiles
                                                .foreach(
                                                    {f =>
                                                        if (f.isDirectory) deleteFolder(makeFolder(f.toString)) else deleteFile(f.toString)
                                                    })
                                            new java.io.File(_x).delete()
                                        }
                                    def xml(_n : scala.xml.Node, _f : String) = 
                                        {
                                            shouldBeFile(_f)
                                            val f = correct(_f)
                                            scala.xml.XML.save(f, _n, "UTF-8")
                                        }
                                    def xml(_f : String) : Option[scala.xml.Node] = 
                                        {
                                            shouldBeFile(_f)
                                            shouldExist(_f)
                                            try 
                                                {
                                                    Some(scala.xml.XML.load(correct(_f)))
                                                } 
                                            catch 
                                                {
                                                    case _ : Throwable =>
                                                        None
                                                }
                                        }
                                    def save(_f : String, _x : String) : Unit = 
                                        {
                                            shouldBeFile(_f)
                                            create(getFolder(_f))  
                                            val s = new java.io.BufferedOutputStream(new java.io.FileOutputStream(_f), 8000)
                                            val buffer = _x.getBytes("UTF-8")
                                            s.write(buffer)
                                            s.flush
                                            s.close
                                        }
                                    def load(_f : String) : String = 
                                        {
                                            shouldBeFile(_f)
                                            shouldExist(_f)
                                            val s = new java.io.BufferedInputStream(new java.io.FileInputStream(_f), 8000)
                                            var result = ""
                                            val buffer = new Array[Byte](4096)
                                            var numBytes = s.read(buffer)
                                            
                                            while (numBytes != -1)
                                                {
                                                    result += new String(buffer.take(numBytes))
                                                    numBytes = s.read(buffer)
                                                }
    
                                            s.close
                                            result
                                        }
                                    def drive(_f : String) : Option[String] = 
                                        {
                                            if (isFullName(_f)) 
                                                {
                                                    var p = new java.io.File(_f)
                                                    while (p.getParentFile != null) p = p.getParentFile 
                                                    Some(p.toString.toUpperCase)
                                                } 
                                            else None
                                        }
                                    def parentFolder(_f : String) : Option[String] =
                                        {
                                            shouldBeFolder(_f)
                                            new java.io.File(_f).getParentFile match
                                                {
                                                    case null =>
                                                        None
                                                    case x =>
                                                        Some(makeFolder(x.toString))
                                                }
                                        }
                                    def relativeTo(_original : String, _r : String) : String = 
                                        {
                                            shouldBeValid(_original)
                                            shouldBeValid(_r)
                                            try 
                                                {
                                                    //System.out.println("Relative To")
                                                    val sep = separator.replace("\\","\\\\")
                                                    var dest = new java.io.File(correct(_original)).getCanonicalPath().split(sep)
                                                    var current = correct(_r).split(sep)
                                                    //System.out.println("    dest " + dest.mkString(", "))
                                                    //System.out.println("    curr " + current.mkString(", "))
                                                    //System.out.println("A")
                                                    if (dest.size > 0 && current.size > 0 && dest(0).toUpperCase != current(0).toUpperCase) return _original
                                                    //System.out.println("B")
                                                    
                                                    while (dest.size > 0 && current.size > 0 && dest(0).toUpperCase == current(0).toUpperCase) 
                                                       {
                                                           //System.out.println("C")
                                                           dest = dest.drop(1)
                                                           current = current.drop(1)
                                                            //System.out.println("    dest " + dest.mkString(", "))
                                                            //System.out.println("    curr " + current.mkString(", "))
                                                       }
                                                    
                                                    val result = (current.map({_ => ".."}) ++ dest).mkString(separator) 
                                                    if (result.size == 0) "" else if (isFolder(_original)) makeFolder(result) else result
                                                } 
                                            catch 
                                                {
                                                    case x : Throwable =>
                                                        x.printStackTrace
                                                        _original
                                                }
                                        }
                                        
                                    def join(_x : String, _y : String) : String = 
                                        {
                                            shouldBeFolder(_x)
                                            shouldBeRelative(_y)
                                            if (isFullName(_x))
                                                {
                                                    val r = new java.io.File(_x + _y).getCanonicalFile.toString
                                                    if (isFolder(_y)) makeFolder(r) else r
                                                } else
                                                {
                                                    val x = correct(_x)
                                                    val y = correct(_y)
                                                    val root = "C:\\" + Range((x + y).filter(_.toString == separator.toString).size).map({_ => "A\\"}).mkString
                                                    val n = new java.io.File(root + x + y).getCanonicalPath
                                                    if (isFolder(y)) makeFolder(relativeTo(n, root)) else relativeTo(n, root) 
                                                }
                                        }
                                    def rename(_x : String, _y : String) : String = 
                                        {
                                            if (isFolder(_x))
                                                {
                                                    parentFolder(_x) match 
                                                        {
                                                            case None => 
                                                                makeFolder(_y)
                                                            case Some(x) =>
                                                                makeFolder(x + _y)
                                                        }
                                                } else
                                                    {
                                                        getFolder(_x) + _y
                                                    }
                                        }
                                    def renameOnDrive(_x : String, _y : String) : String = 
                                        {
                                            val newFileName = rename(_x, _y)
                                            //System.out.println("Rename ")
                                            //System.out.println("    Old file name = " + _x)
                                            //System.out.println("    New file name = " + newFileName)
                                            new java.io.File(_x).renameTo(new java.io.File(newFileName))
                                            newFileName
                                        }
                                    def extension(_x : String) : String = 
                                        {
                                            if (isFile(_x)) 
                                                {
                                                    val p = _x.split("\\.")
                                                    if (p.size == 1) "" else p(p.size-1)
                                                } else ""
                                        }
                                    def files(_x : String) : List[String] = 
                                        {
                                            new java.io.File(_x).listFiles().filter(_.isFile).map({n => new java.io.File(n.toString).getCanonicalPath}).toList
                                        }
                                    def folders(_x : String) : List[String] = new java.io.File(_x).listFiles().filter(_.isDirectory).map(_.toString).toList
                                    def lastModified(_x : String) : Long = new java.io.File(_x).lastModified
                                }
                    // Validator
                        object Validator
                            extends Framework.Validator.iFactory
                                {
                                    import Framework.Validator._
                                    
                                    class SingleValidator[A](val name : String, val isValid : A => Boolean, val message : A => String)
                                        {
                                            
                                        }
                                    
                                    class Class[A](_list : List[SingleValidator[A]])
                                        extends Interface[A]
                                            {
                                                def add(_n : String, _x : A => Boolean, _m : A => String) : Interface[A] = new Class(_list :+ new SingleValidator(_n, _x, _m))
                                                def isValid(_x : A) : Boolean = _list.foldLeft(true)(_ && _.isValid(_x))
                                                def ifValid(_x : A, _f : A => Unit) : this.type = 
                                                    {
                                                        if (isValid(_x)) _f(_x)
                                                        this
                                                    }
                                                def ifNotValid(_x : A, _f : A => Unit) : this.type = 
                                                    {
                                                        if (!isValid(_x)) _f(_x)
                                                        this
                                                    }
                                                def message(_x : A) : Option[String] = 
                                                    {
                                                        val l = 
                                                            _list
                                                                .iterator
                                                                .filter(!_.isValid(_x))
                                                                .map({x => Some(x.message(_x))})
                                                        if (l.hasNext) l.next else None
                                                    }
                                            }
                                    def apply[A]() : Interface[A] = new Class[A](List())
                                }
                    // Sleep
                        object Sleep
                            extends Framework.Sleep.Interface
                                {
                                    def apply(_x : Long) : Unit = 
                                        {
                                            try 
                                                {
                                                    Thread.sleep(_x)
                                                } 
                                            catch 
                                                {
                                                    case _ : Throwable =>
                                                }
                                        }
                                }
                    def initialise() = 
                        {
                            Framework.Range.assign(Implementation.Range)
                            Framework.Cast.assign(Implementation.Cast)
                            Framework.ClipBoard.assign(Implementation.ClipBoard)
                            Framework.Sort.assign(Implementation.Sort)
                            Framework.Cut.assign(Implementation.Cut)
                            Framework.TryCatch.assign(Implementation.TryCatch)
                            Framework.Lists.assign(Implementation.Lists)
                            Framework.Context.factory(Implementation.Context)
                            Framework.FileHandler.assign(Implementation.FileHandler)
                            Framework.Validator.factory(Implementation.Validator)
                            Framework.Sleep.assign(Implementation.Sleep)
                            Framework.Iterate.assign(Implementation.Iterate)
                        }
                }
    }
package nl.datakneder.core.Utils
    {
        import nl.datakneder.core.Packages._
        
        object Test
            extends Template_TestPackage("Utils", 1490176360392L /*CompileDate*/, nl.datakneder.core.Utils.Framework)
                {
                    import nl.datakneder.core.Acid.Framework.Acid

                    class Range(Range : Framework.Range.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Range")
                            {
                                import nl.datakneder.core.Utils.Framework.Range
                                
                                def run() : Unit = 
                                    {
                                        Acid("0-1-2-3-4-5", Range(6).mkString("-"))
                                        Acid("2-3-4-5", Range(2, 6).mkString("-"))
                                        Acid("2-4", Range(2, 6, 2).mkString("-"))
                                        Acid("6-5-4-3-2-1", Range(6, 0, -1).mkString("-"))
                                        Acid("0-1-2-3-4-5", Range(Array(1,2,3,43,5,6)).mkString("-"))
                                    }
                            }
                    class ClipBoard(ClipBoard : Framework.ClipBoard.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("ClipBoard")
                            {
                                import nl.datakneder.core.Utils.Framework.ClipBoard
                                
                                def run() : Unit = 
                                    {
                                        ClipBoard.copyTo("Henk")
                                        Acid("Henk",ClipBoard.content())
                                        Acid("Henk",ClipBoard())
                                        ClipBoard("Herman")
                                        Acid("Herman",ClipBoard.content())
                                        Acid("Herman",ClipBoard())
                                    }
                            }
                    class Sort(Sort : Framework.Sort.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Sort")
                            {
                                import nl.datakneder.core.Utils.Framework.Sort
                                
                                def run() : Unit = 
                                    {
                                        val a = Array(10,3,6,12,8)
                                        Acid(a.mkString("-"),"10-3-6-12-8")
                
                                        val b = Sort(a)
                                        Acid(a.mkString("-"),"10-3-6-12-8")
                                        Acid(b.mkString("-"),"3-6-8-10-12")
                
                                        val a1 = Array(10,3,6,12,6,8)
                                        Acid(a1.mkString("-"),"10-3-6-12-6-8")
                
                                        val b1 = Sort(a1)
                                        Acid(a1.mkString("-"),"10-3-6-12-6-8")
                                        Acid(b1.mkString("-"),"3-6-6-8-10-12")
                
                                        case class A(n : Int)
                                            extends Comparable[A]
                                                {
                                                    def compareTo(acid : A) : Int = n.compareTo(acid.n)
                                                }
                                        val a2 = Array(A(10),A(3),A(6),A(12),A(6),A(8))
                                        Acid(a2.mkString("-"),"A(10)-A(3)-A(6)-A(12)-A(6)-A(8)")
                
                                        val b2 = Sort(a2)
                                        Acid(a2.mkString("-"),"A(10)-A(3)-A(6)-A(12)-A(6)-A(8)")
                                        Acid(b2.mkString("-"),"A(3)-A(6)-A(6)-A(8)-A(10)-A(12)")
                
                                        val a3 = Array(A(10),A(3),A(6),A(12),A(6),A(8),A(10),A(3),A(6),A(12),A(6),A(8))
                                        Acid(a3.mkString("-"),"A(10)-A(3)-A(6)-A(12)-A(6)-A(8)-A(10)-A(3)-A(6)-A(12)-A(6)-A(8)")
                                        val b3 = Sort.unique(a3)
                                        Acid(b3.mkString("-"),"A(3)-A(6)-A(8)-A(10)-A(12)")
                
                                        val a4 = Array(10,3,6,12,6,8,10,3,6,12,6,8)
                                        Acid(a4.mkString("-"),"10-3-6-12-6-8-10-3-6-12-6-8")
                                        val b4 = Sort.unique(a4)
                                        Acid(b4.mkString("-"),"3-6-8-10-12")
                
                                        Acid("9, 7, 5, 3, 1, 0, 2, 4, 6, 8",Sort(scala.collection.immutable.Range(0, 10, 1).toArray,
                                            {(i : Int,j : Int) => 
                                                (i % 2 == 1, j % 2 == 1) match
                                                    {
                                                        case (false,false) => i < j
                                                        case (false,true) => false
                                                        case (true,false) => true
                                                        case (true,true) => i > j
                                                    }
                                            }).mkString(", "))
                
                                        Acid("(1,Een)-(2,Twee)-(3,Drie)",Sort[(Int, String)](Array((3,"Drie"),(2,"Twee"),(1,"Een")),{(i, j) => i._1 < j._1}).mkString("-"))                        
                                    }
                            }
                    class Cast(Cast : Framework.Cast.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Cast")
                            {
                                import nl.datakneder.core.Utils.Framework.Cast
                                
                                def run() : Unit = 
                                    {
                                        Acid(Some("java.awt.Button"),Cast(new java.awt.Button(""), {case p : java.awt.Component => p}).map(_.getClass.getName))
                                        Acid(None, Cast(new java.awt.Button(""), {case p : Int => p}))
                                    }
                            }

                    class StringManipulation(Cut : Framework.Cut.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("StringManipulation")
                            {
                                import nl.datakneder.core.Utils.Framework.Cut
                                
                                def run() : Unit = 
                                    {
                                        Acid(true)
                                        
                                        Acid(0, Cut.index("Jeroen Claassens", "e", 0))
                                        Acid(2, Cut.index("Jeroen Claassens", "e", 1))
                                        Acid(5, Cut.index("Jeroen Claassens", "e", 2))
                                        Acid(14, Cut.index("Jeroen Claassens", "e", 3))
                                        Acid(-1, Cut.index("Jeroen Claassens", "e", 4))
                                        Acid(-1, Cut.index("Jeroen Claassens", "e", 5))
                                        Acid(12, Cut.index("Jeroen Claassens", "e", -1))
                                        Acid(3, Cut.index("Jeroen Claassens", "e", -2))
                                        Acid(0, Cut.index("Jeroen Claassens", "e", -3))
                                        Acid(-1, Cut.index("Jeroen Claassens", "e", -4))
                                        Acid(-1, Cut.index("Jeroen Claassens", "e", -5))
                                        
                                        
                                        Acid("roen Claassens", Cut.from("Jeroen Claassens","e", 1))
                                        Acid("n Claassens", Cut.from("Jeroen Claassens","e", 2))
                                        Acid("ns", Cut.from("Jeroen Claassens","e", 3))
                                        Acid("", Cut.from("Jeroen Claassens","e", 4))
                                        Acid("ns", Cut.from("Jeroen Claassens","e", -1))
                                        Acid("n Claassens", Cut.from("Jeroen Claassens","e", -2))
                                        Acid("roen Claassens", Cut.from("Jeroen Claassens","e", -3))
                                        Acid("Jeroen Claassens", Cut.from("Jeroen Claassens","e", -4))
            
                                        Acid("", Cut.to("Jeroen Claassens", "e", 0))
                                        Acid("J", Cut.to("Jeroen Claassens", "e", 1))
                                        Acid("Jero", Cut.to("Jeroen Claassens", "e", 2))
                                        Acid("Jeroen Claass", Cut.to("Jeroen Claassens", "e", 3))
                                        Acid("Jeroen Claassens", Cut.to("Jeroen Claassens", "e", 4))
                                        Acid("Jeroen Claassens", Cut.to("Jeroen Claassens", "e", 5))
                                        Acid("Jeroen Claass", Cut.to("Jeroen Claassens", "e", -1))
                                        Acid("Jero", Cut.to("Jeroen Claassens", "e", -2))
                                        Acid("J", Cut.to("Jeroen Claassens", "e", -3))
                                        Acid("", Cut.to("Jeroen Claassens", "e", -4))
                                        Acid("", Cut.to("Jeroen Claassens", "e", -5))
                  
                                        
                                        Acid("bbccaabbcc",Cut.from("aabbccaabbccaabbcc", "aa",2))
                                        Acid("aabbcc", Cut.to("aabbccaabbccaabbcc", "aa",2))
                                    }
                            }
                    class TryCatch(TryCatch : Framework.TryCatch.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("TryCatch")
                            {
                                import nl.datakneder.core.Utils.Framework.TryCatch
                                
                                def run() : Unit = 
                                    {
                                        var x = 1
                                        Acid(1, x)
                                        TryCatch({x = 2}, {x = 3})
                                        Acid(2, x)
                                        TryCatch(
                                            {
                                                throw new Exception("")
                                                x = 4
                                            }, {x = 5})
                                        Acid(5, x)
                                        TryCatch(
                                            {
                                                val b : String = null
                                                val q = b.size
                                                x = 6
                                            }, {x = 7})
                                        Acid(7, x)
                                    }
                            }
                    class Lists(Lists : Framework.Lists.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Lists")
                            {
                                import nl.datakneder.core.Utils.Framework.Lists
                                
                                def run() : Unit = 
                                    {
                                        val list = List("A","B","C","D","E","F","G")
                                        Acid("ABDEFCG", Lists.move(2,5,list).mkString)
                                        Acid("BACDEFG", Lists.move(0,1,list).mkString)
                                        Acid("ACBDEFG", Lists.move(1,2,list).mkString)
                                        Acid("ACDEFGB", Lists.move(1,20,list).mkString)
                                        Acid("ABCDEFG", Lists.move(3,3,list).mkString)
    
                                        Acid("ABFCDEG", Lists.move(5,2,list).mkString)
                                        Acid("BACDEFG", Lists.move(1,0,list).mkString)
                                        Acid("ACBDEFG", Lists.move(2,1,list).mkString)
                                        Acid("AGBCDEF", Lists.move(20,1,list).mkString)
                                        Acid("ABCDEFG", Lists.move(3,3,list).mkString)
    
                                        Acid("ABCDEFG", Lists.swap(3,3,list).mkString)
                                        Acid("ABCEDFG", Lists.swap(3,4,list).mkString)
                                        Acid("AECDBFG", Lists.swap(1,4,list).mkString)
    
                                        Acid("ABCEDFG", Lists.swap(4,3,list).mkString)
                                        Acid("AECDBFG", Lists.swap(4,1,list).mkString)
                                    }
                            }
                    class Context(_context : Framework.Context.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Context")
                            {
                                import nl.datakneder.core.Utils.Framework.Context
                                
                                def run() : Unit = 
                                    {
                                        var f : Framework.Context.Interface[Int] = _context[Int]({_ => 0})
                                        Acid(0, f(1))
                                        Acid(0, f(2))
                                        Acid(0, f(3))
                                        Acid(0, f(4))
                                        Acid(0, f("A"))
                                        
                                        val fa = f.add(_ => _ => {case x : Int if (x % 2 == 0) =>  2 * x})
                                        Acid(0, fa(1))
                                            Acid(4, fa(2))
                                        Acid(0, fa(3))
                                        Acid(8, fa(4))
                                        Acid(0, fa("A"))
                                        
                                        val fb = fa.add(_ => _ => {case x : Int if (x % 2 == 1) =>  3 * x})
                                        Acid(3, fb(1))
                                        Acid(4, fb(2))
                                        Acid(9, fb(3))
                                        Acid(8, fb(4))
                                        Acid(0, fb("A"))
                                        
                                        val fc = fb.add(_ => _ => {case 4 =>  100})
                                        Acid(3, fc(1))
                                        Acid(4, fc(2))
                                        Acid(9, fc(3))
                                        Acid(100, fc(4))
                                        Acid(0, fc("A"))
                                        
                                        var c = _context[java.awt.Component]({_ => new javax.swing.JLabel})
                                        Acid("javax.swing.JLabel", c("A").getClass.getName)
                                        Acid("javax.swing.JLabel", c(12).getClass.getName)
                                        Acid("javax.swing.JLabel", c(java.awt.Color.GREEN).getClass.getName)
                                        Acid("javax.swing.JLabel", c('A').getClass.getName)
                                        
                                        c = c.add(_ => _ => {case p : String => new javax.swing.JTextField()})
                                        c = c.add(_ => _ => {case p : Int => new javax.swing.JButton()})
                                        c = c.add(_ => _ => {case p : java.awt.Color => new javax.swing.JColorChooser()})
                                        c = c.add(_ => _ => {case p : Char => new javax.swing.JTextField()})
                                        Acid("javax.swing.JTextField", c("A").getClass.getName)
                                        Acid("javax.swing.JButton", c(12).getClass.getName)
                                        Acid("javax.swing.JColorChooser", c(java.awt.Color.GREEN).getClass.getName)
                                        Acid("javax.swing.JTextField", c('A').getClass.getName)
                                            
                                        val d = c.copy().add(_ => _ => {case p : Int => new javax.swing.JLabel()})
                                        Acid("javax.swing.JTextField", c("A").getClass.getName)
                                        Acid("javax.swing.JButton", c(12).getClass.getName)
                                        Acid("javax.swing.JColorChooser", c(java.awt.Color.GREEN).getClass.getName)
                                        Acid("javax.swing.JTextField", c('A').getClass.getName)
                                        Acid("javax.swing.JTextField", d("A").getClass.getName)
                                        Acid("javax.swing.JLabel", d(12).getClass.getName)
                                        Acid("javax.swing.JColorChooser", d(java.awt.Color.GREEN).getClass.getName)
                                        Acid("javax.swing.JTextField", d('A').getClass.getName)
                                            
                                        // Testing Roots and current
                                        val q = 
                                            _context[String]({_ => ""})
                                                .add(_ => _ => {case "A" => "a"})
                                                .add(_ => _ => {case "B" => "b"})
                                                .add(x => y => {case "N" => x("Q") + "1"})
                                                .add(_ => _ => {case "Q" => "q"})
                                                .add(x => y => {case "M" =>y("A") + "x"}) 
                                                .add(_ => _ => {case "A" => "v"})
                                                
                                        Acid("v", q("A"))
                                        Acid("b", q("B"))
                                        Acid("q1", q("N"))
                                        Acid("q", q("Q"))
                                        Acid("ax", q("M"))
                                    }
                            }
                    class FileHandler(_fileHandler : Framework.FileHandler.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("FileHandler")
                            {
                                import nl.datakneder.core.Utils.Framework.FileHandler
                                
                                def run() : Unit = 
                                    {
                                        {
                                            val f = ""
                                            Acid(false, _fileHandler.isValid(f))
                                            Acid(false, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(false, _fileHandler.isFullName(f))
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "C:D:E:"
                                            Acid(false, _fileHandler.isValid(f))
                                            Acid(false, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(false, _fileHandler.isFullName(f))
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "C:/"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(false, _fileHandler.isFile(f))
                                            Acid(true, _fileHandler.isFolder(f))
                                            Acid(true, _fileHandler.isFullName(f))
                                            Acid(true, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "C:\\"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(false, _fileHandler.isFile(f))
                                            Acid(true, _fileHandler.isFolder(f))
                                            Acid(true, _fileHandler.isFullName(f))
                                            Acid(true, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "C:/A/B"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(true, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(true, _fileHandler.isFullName(f))
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "C:/A/B/"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(false, _fileHandler.isFile(f))
                                            Acid(true, _fileHandler.isFolder(f))
                                            Acid(true, _fileHandler.isFullName(f))
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "a.txt"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(true, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(false, _fileHandler.isFullName(f))
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(true, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "../A/a.txt"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(true, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(false, _fileHandler.isFullName(f))
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(true, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "project.xml"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(true, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(false, _fileHandler.isFullName(f))
                                            Acid(true, _fileHandler.isExisting(f))
                                            Acid(true, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "S:\\02.12.00\\Datakneder\\Development\\Sandbox\\A\\B\\C"
                                            Acid(true, _fileHandler.isValid(f))
                                            Acid(true, _fileHandler.isFile(f))
                                            Acid(false, _fileHandler.isFolder(f))
                                            Acid(true, _fileHandler.isFullName(f))
                                            //Acid(true, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isRelative(f))
                                        }
                                        
                                        {
                                            val f = "project.xml"
                                            val c = "copy.xml"
                                            Acid(true, _fileHandler.isExisting(f))
                                            Acid(false, _fileHandler.isExisting(c))
                                            
                                            _fileHandler.copyFile(f, c)
                                            Acid(true, _fileHandler.isExisting(c))
                                            
                                            _fileHandler.deleteFile(c)
                                            Acid(false, _fileHandler.isExisting(c))
                                            
                                            _fileHandler.save(c, _fileHandler.load(f))
                                            Acid(true, _fileHandler.isExisting(c))
                                            Acid(_fileHandler.xml(f), _fileHandler.xml(c))
                                            _fileHandler.deleteFile(c)
                                            Acid(false, _fileHandler.isExisting(c))
                                        }
                                        
                                        {
                                            val f = "bla.xml"
                                            val xml = <p><q></q></p>
                                            Acid(false, _fileHandler.isExisting(f))
                                            _fileHandler.xml(xml, f)
                                            Acid(true, _fileHandler.isExisting(f))
                                            Acid(Some(xml), _fileHandler.xml(f))
                                            
                                            _fileHandler.deleteFile(f)
                                            Acid(false, _fileHandler.isExisting(f))
                                        }
                                        
                                        {
                                            val f = "c:/A/B/a.txt"
                                            Acid(Some("C:\\"), _fileHandler.drive(f))
                                            val f1 = "q:/A/B/a.txt"
                                            Acid(Some("Q:\\"), _fileHandler.drive(f1))
                                            Acid(None, _fileHandler.drive("a.txt"))
                                        }
                                        
                                        {
                                            val f = "C:/A/B/C/D/E/a.txt"  
                                            val f1 = "C:/A/B/C/F/G/"
                                            Acid("..\\..\\D\\E\\a.txt", _fileHandler.relativeTo(f, f1))
                                            val f2 = "Q:/A/B/C/F/G/"
                                            Acid(f, _fileHandler.relativeTo(f, f2))
                                        }
        
                                        {
                                            val f = "A/B/C/a.txt"
                                            Acid(false, _fileHandler.isExisting(f))
                                            _fileHandler.save(f, "A")
                                            Acid(true, _fileHandler.isExisting(f))
                                            
                                            
                                            
                                            _fileHandler.deleteFile(f)
                                            Acid(false, _fileHandler.isExisting(f))
                                            Acid(true, _fileHandler.isExisting("A/"))
                                            _fileHandler.deleteFolder("A/")
                                            Acid(false, _fileHandler.isExisting("A/"))
                                        }
                                        
                                        {
                                            Acid(None, _fileHandler.parentFolder("C:/"))
                                            val f = "C:/A/B/C/"
                                            Acid(Some("C:\\A\\B\\"), _fileHandler.parentFolder(f))
                                        }
                                        
                                        {
                                            Acid("C:\\A\\B\\", 
                                                _fileHandler.getFolder("C:\\A\\B\\a.txt"))  
                                            Acid("C:\\A\\", 
                                                _fileHandler.getFolder("C:\\A\\a.txt"))  
                                            Acid("C:\\", 
                                                _fileHandler.getFolder("C:\\a.txt"))  
                                            Acid("A\\B\\", 
                                                _fileHandler.getFolder("A\\B\\a.txt"))  
                                            Acid("A\\", 
                                                _fileHandler.getFolder("A\\a.txt"))  
                                            Acid(".\\", 
                                                _fileHandler.getFolder("a.txt"))
                                        }
                                        
                                        // Join
                                            {
                                                Acid("C:\\A\\B\\P\\Q", _fileHandler.join("C:/A/B/C/D/","../../P/Q"))
                                                Acid("C:\\A\\B\\P\\Q\\", _fileHandler.join("C:/A/B/C/D/","../../P/Q/"))
                                                Acid("A\\B\\P\\Q\\", _fileHandler.join("A/B/C/D/","../../P/Q/"))
                                                Acid("..\\P\\Q\\", _fileHandler.join("B/","../../P/Q/"))
                                            }
                                        
                                        {
                                            Acid("C:\\A\\B\\C\\D\\b.txt", _fileHandler.rename("C:/A/B/C/D/a.txt","b.txt"))
                                            Acid("C:\\A\\B\\C\\E\\", 
                                                _fileHandler.rename("C:/A/B/C/D/","E\\"))
                                            Acid("txt", _fileHandler.extension("C:/A/B/C/D/a.txt"))
                                            Acid("", _fileHandler.extension("C:/A/B/C/D/E/"))
                                            Acid("", _fileHandler.extension("C:/A/B/C/D/E/q"))
    
                                            _fileHandler.save("A/a.txt","A")
                                            _fileHandler.save("A/b.txt","B")
                                            _fileHandler.save("A/c.txt","C")
                                            
                                            Acid("a.txt, b.txt, c.txt", _fileHandler.files("A/").map({f => _fileHandler.relativeTo(f.toString,new java.io.File("A/").getCanonicalFile().toString)}).mkString(", "))
                                            
                                            _fileHandler.create("A/B/")
                                            _fileHandler.create("A/C/")
                                            _fileHandler.create("A/D/")
                                            Acid("B, C, D", _fileHandler.folders("A/").map({f => _fileHandler.relativeTo(f.toString,new java.io.File("A/").getCanonicalFile().toString)}).mkString(", "))
                                            
                                            _fileHandler.save("A/q.txt","A")
                                            Acid(System.currentTimeMillis - _fileHandler.lastModified("A/q.txt") < 1000) 
                                            
                                            _fileHandler.deleteFolder("A/")
                                            Acid.not(_fileHandler.isExisting("A/"))
                                        }
                                        
                                        {
                                            val f = "c:\\A\\"  
                                            val g = "c:\\A\\B\\C\\"
                                            Acid("..\\..\\", _fileHandler.relativeTo(f, g))
                                            Acid("B\\C\\", _fileHandler.relativeTo(g, f))
                                        }
                                        
                                        {
                                            val f = "c:\\A\\Q\\F\\"  
                                            val g = "c:/A/B/C/"
                                            Acid("..\\..\\Q\\F\\", _fileHandler.relativeTo(f, g))
                                            Acid("..\\..\\B\\C\\", _fileHandler.relativeTo(g, f))
                                        }
        
                                        {
                                            Acid("a.txt", _fileHandler.getFileName("C:\\a.txt"))   
                                            Acid("a.txt", _fileHandler.getFileName("C:\\A\\B\\a.txt"))   
                                            Acid("a.txt", _fileHandler.getFileName("A\\B\\a.txt"))   
                                        }
                                        
                                        // Renaming File
                                            {
                                                val f = "S:\\bla.xml"
                                                Acid(!_fileHandler.isExisting(f))
                                                _fileHandler.xml({<p/>}, f)
                                                Acid(_fileHandler.isExisting(f))
                                                val g = "bla2.xml"
                                                Acid(!_fileHandler.isExisting(g))
                                                val q = _fileHandler.renameOnDrive(f, g)
                                                Acid(_fileHandler.isExisting(q))
                                                _fileHandler.deleteFile(q)
                                            }
                                        
                                        // Renaming Folder
                                            {
                                                val f = "S:\\bla\\A\\"
                                                Acid(!_fileHandler.isExisting(f))
                                                _fileHandler.create(f)
                                                Acid(_fileHandler.isExisting(f))
                                                Acid(_fileHandler.isExisting("S:\\bla\\"))
                                                Acid(!_fileHandler.isExisting("S:\\bla2\\"))
                                                val q = _fileHandler.renameOnDrive("S:\\bla\\", 
                                                    "bla2")
                                                Acid(_fileHandler.isExisting("S:\\bla2\\"))
                                                _fileHandler.deleteFolder("S:\\bla2\\")
                                            }
                                        
                                        // getFileName
                                            {
                                                Acid.exception({_fileHandler.getFileName("C:\\A\\B\\")})
                                            }
                                            
                                        Acid(_fileHandler.isFile("C:/A/B/C"))
                                        Acid.not(_fileHandler.isFolder("C:/A/B/C"))
                                        Acid(_fileHandler.isFolder(_fileHandler.makeFolder("C:/A/B/C")))
                                    }
                            }
                    class Validator(_validator : Framework.Validator.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Validator")
                            {
                                import nl.datakneder.core.Utils.Framework.Validator
                                
                                def run() : Unit = 
                                    {
                                        // ???
                                            {
                                                var p = _validator[String]()
                                                Acid(true, p.isValid("A"))
                                                Acid(None, p.message("A"))
                                                p.ifNotValid("A", {(x : String) => Acid(false)})
                                            }
    
                                            {
                                                val p = 
                                                    _validator[String]()
                                                        .add("ShouldBeNotEmpty",{x => x.size > 0}, {x => "Given string is empty."})
                                                Acid(true, p.isValid("A"))
                                                Acid(false, p.isValid(""))
                                                Acid(None, p.message("A"))
                                                Acid(Some("Given string is empty."), p.message(""))
                                                p.ifNotValid("A", {(x : String) => Acid(false)})
                                                p.ifValid("", {(x : String) => Acid(false)})
                                            }
                                            
                                            {
                                                val p = 
                                                    _validator[String]()
                                                        .add("ShouldBeNotEmpty",{x => x.size > 0}, {x => "Given string is empty."})
                                                        .add("Max 5 characters",{x => x.size <= 5}, {x => "'" + x + "' is too long."})
                                                Acid(true, p.isValid("A"))
                                                Acid(false, p.isValid("ABCDEFG"))
                                                Acid(None, p.message("A"))
                                                Acid(false, p.isValid(""))
                                                Acid(Some("'ABCDEFG' is too long."), p.message("ABCDEFG"))
                                                Acid(Some("Given string is empty."), p.message(""))
                                                p.ifNotValid("A", {(x : String) => Acid(false)})
                                                p.ifValid("ABCDEFG", {(x : String) => Acid(false)})
                                                p.ifValid("", {(x : String) => Acid(false)})
                                                
                                            }
                                    }
                            }
                    class Iterate(Iterate : Framework.Iterate.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Iterate")
                            {
                                def run() : Unit = 
                                    {
                                        case class A(name : String, item : A*)
                                            {
                                                private var __parent : Option[A] = None
                                                def parent() : Option[A] = __parent
                                                def parent(_x : A) : Unit = __parent = Some(_x)
                                                item.foreach(_.parent(this))
                                            }
                                            
                                        val q = A("Q")
                                        val p = A("P", q)
                                        val r = A("R", p)
                                        
                                        Acid("QPR", Iterate.option[A](q, {(p : A) => p.parent()}).map(_.name).mkString)
                                    }
                            }
                    class Sleep(Sleep : Framework.Sleep.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Sleep")
                            {
                                import nl.datakneder.core.Utils.Framework.Sleep
                                
                                def run() : Unit = 
                                    {
                                        val t = System.currentTimeMillis
                                        Sleep(1000)
                                        Acid(System.currentTimeMillis - t >= 1000)
                                    }
                            }
                }
    }
    
    
    