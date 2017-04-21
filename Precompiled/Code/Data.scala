package nl.datakneder.core.Data
    {
        import scala.language.implicitConversions
        import nl.datakneder.core.Packages._
        
        object Framework
            extends Template_FrameworkPackage("Data", 1492773806046L /*CompileDate*/)
                {
                    import nl.datakneder.core.Utils.Framework.Reflect
                    
                    // Keys
                        object KeySequence
                            {
                                trait Interface
                                    {
                                        def +(_x : String) : Interface 
                                        def +(_x : Interface) : Interface 
                                        def <=(_x : Interface) : Boolean
                                        def ==(_x : Interface) : Boolean                                
                                        def onlyModifiers() : Interface
                                        def noModifiers() : Interface
                                    }
                            }
                        object KeyManager
                            extends Template_Object("KeyManager")
                                {
                                    trait Interface
                                        {
                                            def keyDown(_key : java.awt.event.KeyEvent) : this.type 
                                            def keyUp(_key : java.awt.event.KeyEvent) : this.type 
                                            def clear() : Unit 
                                            def clearNonModifiers() : Unit
                                            def noKeysDown() : Boolean
                                            def currentSequence() : KeySequence.Interface
                                            def NoKey : KeySequence.Interface
                                            // Constructors
                                                def sequence(_x : String) : KeySequence.Interface
                                        }
                                        
                                    def keyDown(_key : java.awt.event.KeyEvent) : this.type = Reflect(interface().keyDown(_key), this)
                                    def keyUp(_key : java.awt.event.KeyEvent) : this.type  = Reflect(interface().keyUp(_key), this)
                                    def clear() : Unit = interface().clear()
                                    def clearNonModifiers() : Unit = interface().clearNonModifiers()
                                    def noKeysDown() : Boolean = interface().noKeysDown()
                                    def currentSequence() : KeySequence.Interface = interface().currentSequence()
                                    def NoKey : KeySequence.Interface = interface().NoKey
                                    // Constructors
                                        def sequence(_x : String) : KeySequence.Interface = interface().sequence(_x)
                                }
                        object KeyDefinitions
                            {
                                val NoKey = KeyManager.NoKey
                                val NoModifier = KeyManager.NoKey
                                lazy val SHIFT = KeyManager.sequence("SHIFT")
                                lazy val ALT = KeyManager.sequence("ALT")
                                lazy val CTRL = KeyManager.sequence("CONTROL")
                                lazy val VK_Escape = KeyManager.sequence("ESCAPE")
                                lazy val VK_Enter = KeyManager.sequence("ENTER")
                                lazy val VK_F1 = KeyManager.sequence("F1")
                                lazy val VK_F2 = KeyManager.sequence("F2")
                                lazy val VK_F3 = KeyManager.sequence("F3")
                                lazy val VK_F4 = KeyManager.sequence("F4")
                                lazy val VK_F5 = KeyManager.sequence("F5")
                                lazy val VK_F6 = KeyManager.sequence("F6")
                                lazy val VK_F7 = KeyManager.sequence("F7")
                                lazy val VK_F8 = KeyManager.sequence("F8")
                                lazy val VK_F9 = KeyManager.sequence("F9")
                                lazy val VK_F10 = KeyManager.sequence("F10")
                                lazy val VK_F11 = KeyManager.sequence("F11")
                                lazy val VK_F12 = KeyManager.sequence("F12")
                                lazy val VK_Equals = KeyManager.sequence("EQUALS")
                                lazy val VK_Delete = KeyManager.sequence("DELETE")
                                lazy val VK_Up = KeyManager.sequence("UP")
                                lazy val VK_Down = KeyManager.sequence("DOWN")
                            }
                    // Files
                        object FileOrFolder
                            extends Template_Factory("FileOrFolder")
                                {
                                    trait iHasContent
                                        {
                                            def apply() : String 
                                            def apply(_x : String) : Interface 
                                        }
                                    trait Interface
                                        extends iHasContent
                                            {
                                                def mustBeFile() : File.Interface 
                                                def mustBeFolder() : Folder.Interface
                                            }
                                    trait iFactory
                                        {
                                            def apply(_x : String) : Interface
                                        }
                                    def apply(_x : String) : Interface = factory()(_x)
                                }
                        object File
                            extends Template_Factory("File")
                                {
                                    trait iBasicFile[FolderType, SelfType <: iBasicFile[FolderType, SelfType]]
                                        {
                                            def fileName() : String
                                            def folder() : FolderType 
                                            def rename(_x : String) : SelfType
                                        }
                                    trait iCanSave
                                        {
                                            def xml(_x : scala.xml.Node) : ExistingFile.Interface 
                                            def content(_x : String) : ExistingFile.Interface 
                                        }
                                    trait iAbsoluteFile
                                        {
                                            def relativeTo(_x : AbsoluteFolder.Interface) : RelativeFile.Interface
                                        }
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with iBasicFile[Folder.Interface, Interface]
                                            {
                                                def mustExist() : ExistingFile.Interface 
                                                def mustBeAbsolute() : AbsoluteFile.Interface 
                                                def mustBeRelative() : RelativeFile.Interface 
                                            }
                                    trait iFactory
                                        {
                                            def apply(_f : Folder.Interface, _x : String) : Interface
                                        }
                                    def apply(_f : Folder.Interface, _x : String) : Interface = factory()(_f, _x)
                                }
                        object AbsoluteFile
                            extends Template_Factory("AbsoluteFile")
                                {
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with File.iBasicFile[AbsoluteFolder.Interface, Interface]
                                        with File.iCanSave
                                        with File.iAbsoluteFile
                                            {
                                                def mustExist() : ExistingFile.Interface 
                                            }
                                    trait iFactory
                                        {
                                            def apply(_f : AbsoluteFolder.Interface, _x : String) : Interface
                                        }
                                    def apply(_f : AbsoluteFolder.Interface, _x : String) : Interface = factory()(_f, _x)
                                }
                        object RelativeFile
                            extends Template_Factory("RelativeFile")
                                {
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with File.iBasicFile[RelativeFolder.Interface, Interface]
                                            {
                                            }
                                    trait iFactory
                                        {
                                            def apply(_f : RelativeFolder.Interface, _x : String) : Interface
                                        }
                                    def apply(_f : RelativeFolder.Interface, _x : String) : Interface = factory()(_f, _x)
                                }
                        object ExistingFile
                            extends Template_Factory("ExistingFile")
                                {
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with File.iBasicFile[ExistingFolder.Interface, Interface]
                                        with File.iCanSave
                                        with File.iAbsoluteFile
                                            {
                                                def xml() : scala.xml.Node 
                                                def content() : String 
                                                def delete() : AbsoluteFile.Interface 
                                                def copy(_f : File.Interface) : ExistingFile.Interface 
                                            }
                                    trait iFactory
                                        {
                                            def apply(_f : ExistingFolder.Interface, _x : String) : Interface
                                        }
                                    def apply(_f : ExistingFolder.Interface, _x : String) : Interface = factory()(_f, _x)
                                }
                        object Folder
                            extends Template_Factory("Folder")
                                {
                                    trait iBasicFolder[SelfType <: iBasicFolder[SelfType]]
                                        {
                                            def rename(_x : String) : SelfType
                                        }
                                    trait iJoinable[JoinTypeFolder, JoinTypeFile]
                                        {
                                            def join(_x : RelativeFolder.Interface) : JoinTypeFolder 
                                            def join(_x : RelativeFile.Interface) : JoinTypeFile                                     
                                        }
                                    trait iAbsoluteFolder
                                        {
                                            def relativeTo(_x : AbsoluteFolder.Interface) : RelativeFolder.Interface
                                        }
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with iBasicFolder[Interface]
                                            {
                                                def create() : ExistingFolder.Interface 
                                                def mustExist() : ExistingFolder.Interface 
                                                def mustBeAbsolute() : AbsoluteFolder.Interface 
                                                def mustBeRelative() : RelativeFolder.Interface 
                                            }
                                    trait iFactory
                                        {
                                            def apply(_x : String) : Interface
                                        }
                                    def apply(_x : String) : Interface = factory()(_x)
                                }
                        object AbsoluteFolder
                            extends Template_Factory("AbsoluteFolder")
                                {
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with Folder.iBasicFolder[Interface]
                                        with Folder.iJoinable[Interface, AbsoluteFile.Interface]
                                        with Folder.iAbsoluteFolder
                                            {
                                                def mustExist() : ExistingFolder.Interface 
                                                 
                                            }
                                    trait iFactory
                                        {
                                            def apply(_x : String) : Interface
                                        }
                                    def apply(_x : String) : Interface = factory()(_x)
                                }
                        object RelativeFolder
                            extends Template_Factory("RelativeFolder")
                                {
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with Folder.iBasicFolder[Interface]
                                        with Folder.iJoinable[Interface, RelativeFile.Interface]
                                            {
                                            }
                                    trait iFactory
                                        {
                                            def apply(_x : String) : Interface
                                        }
                                    def apply(_x : String) : Interface = factory()(_x)
                                }
                        object ExistingFolder
                            extends Template_Factory("ExistingFolder")
                                {
                                    trait Interface
                                        extends FileOrFolder.iHasContent
                                        with Folder.iBasicFolder[Interface]
                                        with Folder.iJoinable[AbsoluteFolder.Interface, AbsoluteFile.Interface]
                                        with Folder.iAbsoluteFolder
                                            {
                                                def delete() : Folder.Interface 
                                                def copy(_f : Folder.Interface) : ExistingFolder.Interface 
                                            }
                                    trait iFactory
                                        {
                                            def apply(_x : String) : Interface
                                        }
                                    def apply(_x : String) : Interface = factory()(_x)
                                }
                    // Atoms
                        // Storage
                            // Storage is for now simple xml-format. A generic way to serialize Atoms to XML and vice versa.
                                type BasicStorageType = scala.xml.Node
                        // Utilities
                            // XMLUtils
                                implicit def InjectXMLUtils(_x : scala.xml.Node) =
                                    new Object {
                                        def print() : scala.xml.Node = XMLUtils.print(_x)
                                    }
                                object XMLUtils
                                    extends Template_Object("XMLUtils") 
                                        {
                                            trait Interface
                                                {
                                                    def extractText(_x : scala.xml.Node) : String
                                                    def print(_x : scala.xml.Node) : scala.xml.Node 
                                                    def label(_xml : scala.xml.Node) : String
                                                    def label(_xml : scala.xml.Node, _a : String) : scala.xml.Node
                                                    def label(_x : String) : String
                                                    def attribute(_xml : scala.xml.Node, _a : String) : Option[String]
                                                    def addAttribute(_xml : scala.xml.Node, _name : String, _content : String) : scala.xml.Node
                                                }
                                                
                                            def extractText(_x : scala.xml.Node) : String = interface().extractText(_x)
                                            def print(_x : scala.xml.Node) : scala.xml.Node = interface().print(_x) 
                                            def label(_xml : scala.xml.Node) : String = interface().label(_xml)
                                            def label(_x : String) : String = interface().label(_x)
                                            def label(_xml : scala.xml.Node, _a : String) : scala.xml.Node = interface().label(_xml, _a)
                                            def attribute(_xml : scala.xml.Node, _a : String) : Option[String] = interface().attribute(_xml, _a)
                                            def addAttribute(_xml : scala.xml.Node, _name : String, _content : String) : scala.xml.Node = interface().addAttribute(_xml, _name, _content)
                                        }
                            object BasicStorageTypeWriter
                                extends Template_Object("BasicStorageTypeWriter")
                                    {
                                        trait Interface
                                            {
                                                def apply(_x : BasicStorageType) : Boolean
                                            }
                                        def apply(_x : BasicStorageType) : Boolean = interface()(_x) 
                                    }
                            object BasicStorageTypeReader
                                extends Template_Object("BasicStorageTypeReader")
                                    {
                                        trait Interface
                                            {
                                                def apply(_x : BasicStorageType) : BasicStorageType
                                            }
                                        def apply(_x : BasicStorageType) : BasicStorageType = interface()(_x)
                                    }
                        // Atoms
                            object Atom
                                {
                                    trait Interface[AtomType <: Atom.Interface[AtomType]]
                                        {
                                            def copy() : AtomType
                                            def fill(_x : BasicStorageType) : this.type
                                            def serialize() : BasicStorageType 
                                            def serialize(_typed : Boolean) : BasicStorageType 
                                        }
                                }
                            object ValueAtom
                                {
                                    trait Interface[ValueAtomType <: ValueAtom.Interface[ValueAtomType, JavaType], JavaType]
                                        extends Atom.Interface[ValueAtomType]
                                            {
                                                def apply() : JavaType
                                                def apply(_x : JavaType) : this.type
                                            }
                                }
                            // Instances
                                protected abstract class ValueAtomTemplate[JavaType](_name : String)
                                    extends Template_Factory(_name)
                                        {
                                            type Interface <: ValueAtom.Interface[Interface, JavaType]
                                            trait iFactory
                                                {
                                                    def apply(_x : JavaType) : Interface
                                                }
                                            
                                            def apply(_x : JavaType) : Interface = factory()(_x) 
                                        }
                                object TextAtom
                                    extends ValueAtomTemplate[String]("TextAtom")
                                        {
                                            trait Interface
                                                extends ValueAtom.Interface[TextAtom.Interface, String]
                                                    {
                                                    }
                                        }
                                object NumberAtom
                                    extends ValueAtomTemplate[Int]("NumberAtom")
                                        {
                                            trait Interface
                                                extends ValueAtom.Interface[NumberAtom.Interface, Int]
                                                    {
                                                    }
                                        }
                                object YesNoAtom
                                    extends ValueAtomTemplate[Boolean]("YesNoAtom")
                                        {
                                            trait Interface
                                                extends ValueAtom.Interface[Interface, Boolean]
                                                    {
                                                    }
                                        }
                                object KeysAtom
                                    extends ValueAtomTemplate[KeySequence.Interface]("KeysAtom")
                                        {
                                            trait Interface
                                                extends ValueAtom.Interface[Interface, KeySequence.Interface]
                                                    {
                                                    }
                                        }
                                //object FileOrFolderAtom
                                //    extends ValueAtomTemplate[PlainFileOrFolder.Interface]("FileOrFolderAtom")
                                //        {
                                //            trait Interface
                                //                extends ValueAtom.Interface[Interface, PlainFileOrFolder.Interface]
                                //                    {
                                //                    }
                                //        }
                                object AtomListAtom
                                    extends Template_Factory("AtomListAtom")
                                        {
                                            trait iConstructor[A <: Atom.Interface[_]]
                                                {
                                                    def name() : String
                                                    def apply() : A
                                                }
                                            trait Interface[A <: Atom.Interface[_]]
                                                extends ValueAtom.Interface[Interface[A], List[A]]
                                                    {
                                                        def clear() : this.type
                                                        def add[C <: A](_x : C) : C
                                                        def add(_x : A, _y : A, _z : A*) : this.type
                                                        def apply() : List[A]
                                                        def size() : Int
                                                        def constructors() : List[iConstructor[A]]
                                                        def constructors(_x : List[iConstructor[A]]) : this.type
                                                        def construct(_x : String) : Option[A]
                                                    }
                                            trait iFactory
                                                {
                                                    def apply[A <: Atom.Interface[_]](_constructors : (String, () => A)*) : Interface[A]
                                                    def apply[A <: Atom.Interface[_]](_constructors : List[iConstructor[A]]) : Interface[A]
                                                }
                                            
                                            def apply[A <: Atom.Interface[_]](_constructors : (String, () => A)*) : Interface[A] = factory()(_constructors :_*)
                                            def apply[A <: Atom.Interface[_]](_constructors : List[iConstructor[A]]) : Interface[A] = factory()(_constructors)
                                        }
                                object StringListAtom
                                    extends Template_Factory("StringListAtom")
                                        {
                                            trait Interface
                                                extends ValueAtom.Interface[Interface, List[String]]
                                                    {
                                                        def clear() : this.type
                                                        def add(_x : String*) : this.type
                                                        def remove(_x : String) : this.type
                                                        def contains(_x : String) : Boolean
                                                        def apply() : List[String]
                                                        def size() : Int
                                                    }
                                            trait iFactory
                                                {
                                                    def apply() : Interface
                                                }
                                            
                                            def apply() : Interface = factory()()
                                        }
                    // Calculated Values
                        object CalculatedValue
                            extends Template_Factory("Calculated Value")
                                {
                                    trait Interface[A]
                                        {
                                            def isDirty(_t : Long) : Boolean
                                            def dependencies() : List[Interface[_]]
                                            def apply() : A
                                            def apply(_t : Long) : A
                                            def default(_t : Long, _x : A) : A
                                            def apply(_x : A) : Unit
                                            def value(_t : Long) : Option[A]
                                            def value(_f : Long => A, _d : Interface[_]*) : Unit
                                        }
                                    trait iFactory
                                        {
                                            def apply[A](_x : A) : Interface[A]
                                            def apply[A](_f : Long => A, _d : Interface[_]*) : Interface[A]
                                        }
                                    def apply[A](_x : A) : Interface[A] = factory()(_x)
                                    def apply[A](_f : Long => A, _d : Interface[_]*) : Interface[A] = factory()(_f, _d :_*)
                                }
                }
    }
package nl.datakneder.core.Data
    {
        import nl.datakneder.core.Packages._
        import scala.language.reflectiveCalls
        import nl.datakneder.core.Utils._
        
        object Implementation
            extends Template_ImplementationPackage("Data", 1492773806046L /*CompileDate*/, nl.datakneder.core.Data.Framework)
                {
                    import nl.datakneder.core.Utils.Framework._
                    import Framework.KeyDefinitions.NoModifier
                    //import nl.datakneder.core.Utils.Framework.Reflect
                    
                    // Keys
                        //val iKeys = nl.datakneder.core.Data.Framework
                        import Framework.KeySequence
                        
                        object KeyManager
                            extends Framework.KeyManager.Interface
                                {                
                                    object Private
                                        {
                                            var __keysDown : List[String] = List[String]()
                                            var __sequence : KeySequence.Interface = EmptyKeySequence
                                            val __mods = List[String]("ALT","ALT_GRAPH", "CTRL","META","SHIFT")
                                            case class Sequence(modifiers : List[String], keys : List[String])
                                                extends KeySequence.Interface
                                                    {
                                                        def +(_x : String) : KeySequence.Interface = Sequence(modifiers, keys ++ _x.map({c => c.toString}))
                                                        def +(_x : KeySequence.Interface) : KeySequence.Interface = 
                                                            {
                                                                _x match
                                                                    {
                                                                        case x : Sequence =>
                                                                            Sequence(__mods.filter({m => x.modifiers.contains(m) || modifiers.contains(m)}), keys ++ x.keys)
                                                                        case _ =>
                                                                            this
                                                                    }
                                                            }
                                                        override def toString = (modifiers.map(_.toString.toLowerCase).toList ++ keys.map(_.toString)).mkString(" ")
                                                        def <=(_x : KeySequence.Interface) : Boolean = 
                                                            {
                                                                _x match
                                                                    {
                                                                        case x : Sequence =>
                                                                            val a = x.modifiers.filter(!modifiers.contains(_)).size == 0 
                                                                            val b = (keys.size <= x.keys.size) 
                                                                            val c = (keys.iterator.zip(x.keys.iterator).filter({p => p._1 == p._2}).size == keys.size)
                                                                            //System.out.println("Result: " + (toString, x.toString, a,b,c))
                                                                            a && b &&c
                                                                        case _ =>
                                                                            false
                                                                    }
                                                            }
                                                        def ==(_x : KeySequence.Interface) : Boolean = 
                                                            {
                                                                _x match
                                                                    {
                                                                        case x : Sequence =>
                                                                            val a = (x.modifiers == modifiers)  
                                                                            val b = (keys.size == x.keys.size) 
                                                                            val c = (keys.iterator.zip(x.keys.iterator).filter({p => p._1 == p._2}).size == keys.size)
                                                                            //System.out.println("Result: " + ((modifiers.code, toString), (x.modifiers.code, x.toString), a,b,c))
                                                                            a && b &&c
                                                                        case _ =>
                                                                            false
                                                                    }
                                                            }
                                                        def onlyModifiers() : KeySequence.Interface = Sequence(modifiers, List())
                                                        def noModifiers() : KeySequence.Interface = Sequence(List(), keys)
                                                    }
                                            object EmptyKeySequence
                                                extends Sequence(List[String](), List[String]())
                                            def isModifier(_x : String) : Boolean = __mods.contains(_x)
                                        }
                                        import Private._
                                    def clear() : Unit = 
                                        {
                                            __sequence = EmptyKeySequence
                                            __keysDown = List[String]()
                                        }
                                    def clearNonModifiers() : Unit = 
                                        {
                                            __sequence = __sequence.onlyModifiers()
                                            __keysDown = __keysDown.filter(isModifier(_))                                    
                                        }
                                    def currentSequence() : KeySequence.Interface = __sequence
                                    def keyDown(_key : java.awt.event.KeyEvent) : this.type = 
                                        {
                                            //System.out.println("A: Key = " + _key)
                                            if (noKeysDown) __sequence = EmptyKeySequence
                                            val fullKey : KeySequence.Interface = sequence(javax.swing.KeyStroke.getKeyStroke(_key.getKeyCode, _key.getModifiers).toString)
                                            val l = fullKey.toString.split(" ")
                                            __sequence = __sequence + fullKey
                                            __keysDown = __keysDown.filter({k => !l.contains(k)}) ++ l
                                            //System.out.println("A: Keys down: " + __keysDown.mkString(" "))
                                            this
                                        }
                                    def keyUp(_key : java.awt.event.KeyEvent) : this.type = 
                                        {
                                            //System.out.println("Up: Key = " + _key)
                                            val fullKey : KeySequence.Interface = sequence(javax.swing.KeyStroke.getKeyStroke(_key.getKeyCode, 0 /*_key.getModifiers*/).toString)
                                            val l = fullKey.toString.split(" ")
                                            //System.out.println("Key up: " + (fullKey.noModifiers(), __keysDown.size))
                                            __keysDown = __keysDown.filter({k => !l.contains(k)})
                                            //System.out.println("B: Keys down: " + __keysDown.mkString(" "))
                                            this
                                        }
                                    def NoKey = EmptyKeySequence
                                    def noKeysDown() : Boolean = __keysDown.size == 0
                                    // Constructors
                                        def sequence(_x : String) : KeySequence.Interface =
                                            {
                                                val l = _x.toUpperCase.replace("CONTROL", "CTRL").split(" ").filter(_ != "PRESSED").toList
                                                val result = new Sequence(__mods.filter(l.contains(_)), l.filter(!isModifier(_))) 
                                                //System.out.println("Sequence => '" + _x + "'  Modifiers: '" + result.onlyModifiers() + "', Sequence: '" + result.noModifiers + "'")
                                                result
                                                
                                            }
                                }
                    // Files
                        object FileOrFolder
                            extends Framework.FileOrFolder.iFactory
                                {
                                    import Framework.FileOrFolder._
                                    
                                    class Class(_name : String)
                                        extends Interface
                                            {
                                                override def toString = "FileOrFolder(" + _name + ")"
                                                def apply() : String = _name
                                                def apply(_x : String) : Interface = new Class(_x)
                                                def mustBeFile() : Framework.File.Interface = new File.Class(new Folder.Class(FileHandler.getFolder(_name)), FileHandler.getFileName(_name))
                                                def mustBeFolder() : Framework.Folder.Interface = new Folder.Class(_name)
                                            }
                                    
                                    def apply(_x : String) : Interface = new Class(_x) 
                                }
                        object File
                            extends Framework.File.iFactory
                                {
                                    //import Framework.File.{Framework.AbsoluteFile.Interface => _, _}
                                  
                                    abstract class AClass[FolderType <: Framework.FileOrFolder.iHasContent](_typeName : String, _folder : FolderType, _name : String)
                                        {
                                            override def toString = _typeName + "(" + folder()() + _name + ")"
                                            def fileName() : String = _name
                                            def folder() : FolderType = _folder
                                            def apply() : String = folder()() + _name
                                            def apply(_x : String) : Framework.FileOrFolder.Interface = new FileOrFolder.Class(folder()() + _x)
                                        }
                                    
                                    class Class(_folder : Framework.Folder.Interface, _name : String)
                                        extends AClass[Framework.Folder.Interface]("File", _folder, _name)
                                        with Framework.File.Interface
                                            {
                                                if (!FileHandler.isFile(apply())) throw new Exception("'" + apply() + "' is not a file.")
                                                def mustExist() : Framework.ExistingFile.Interface = new ExistingFile.Class(folder.mustExist, _name)
                                                def mustBeAbsolute() : Framework.AbsoluteFile.Interface = new AbsoluteFile.Class(folder.mustBeAbsolute, _name)
                                                def mustBeRelative() : Framework.RelativeFile.Interface = new RelativeFile.Class(folder.mustBeRelative, _name)
                                                def rename(_x : String) : Framework.File.Interface = new File.Class(folder, _x)
                                            }
                                    
                                    def apply(_f : Framework.Folder.Interface, _x : String) : Framework.File.Interface = new Class(_f, _x)
                                }
                        object AbsoluteFile
                            extends Framework.AbsoluteFile.iFactory
                                {
                                    import Framework.AbsoluteFile._
                                    
                                    class Class(_folder : Framework.AbsoluteFolder.Interface, _name : String)
                                        extends File.AClass[Framework.AbsoluteFolder.Interface]("AbsoluteFile", _folder, _name)
                                        with Interface
                                            {
                                                if (!(FileHandler.isFile(apply()) && FileHandler.isFullName(apply()))) throw new Exception("'" + apply() + "' is not an absolute folder.")
                                                def rename(_x : String) : Framework.AbsoluteFile.Interface = new AbsoluteFile.Class(folder, _x)
                                                def mustExist() : Framework.ExistingFile.Interface = new ExistingFile.Class(folder.mustExist, _name)
                                                def relativeTo(_x : Framework.AbsoluteFolder.Interface) : Framework.RelativeFile.Interface = new RelativeFile.Class(folder.relativeTo(_x), _name)
                                                def xml(_x : scala.xml.Node) : Framework.ExistingFile.Interface = 
                                                    {
                                                        FileHandler.xml(_x, apply())
                                                        mustExist()
                                                    }
                                                def content(_x : String) : Framework.ExistingFile.Interface = 
                                                    {
                                                        FileHandler.save(apply(), _x)
                                                        mustExist()
                                                    } 
                                            }
                                    
                                    def apply(_f : Framework.AbsoluteFolder.Interface, _x : String) : Interface = new Class(_f, _x)
                                }
                        object RelativeFile
                            extends Framework.RelativeFile.iFactory
                                {
                                    import Framework.RelativeFile._
                                    
                                    class Class(_folder : Framework.RelativeFolder.Interface, _name : String)
                                        extends File.AClass[Framework.RelativeFolder.Interface]("RelativeFile", _folder, _name)
                                        with Interface
                                            {
                                                if (!(FileHandler.isFile(apply()))) throw new Exception("'" + apply() + "' is not a relative folder.")
                                                def rename(_x : String) : Framework.RelativeFile.Interface = new RelativeFile.Class(folder, _x)
                                            }
                                    
                                    def apply(_f : Framework.RelativeFolder.Interface, _x : String) : Interface = new Class(_f, _x) 
                                }
                        object ExistingFile
                            extends Framework.ExistingFile.iFactory
                                {
                                    import Framework.ExistingFile._
                                    
                                    class Class(_folder : Framework.ExistingFolder.Interface, _name : String)
                                        extends File.AClass[Framework.ExistingFolder.Interface]("ExistingFile", _folder, _name)
                                        with Interface
                                            {
                                                if (!(FileHandler.isExisting(apply()))) throw new Exception("'" + apply() + "' is not an existing folder.")
                                                def xml() : scala.xml.Node = FileHandler.xml(apply()).getOrElse(<ErrorReadingXMLFile/>)
                                                def xml(_x : scala.xml.Node) : this.type = Reflect({FileHandler.xml(_x, apply())}, this)
                                                def content() : String = FileHandler.load(apply())
                                                def content(_x : String) : this.type = Reflect({FileHandler.save(apply(), _x)}, this) 
                                                def delete() : Framework.AbsoluteFile.Interface = 
                                                    {
                                                        FileHandler.deleteFile(_name)
                                                        new AbsoluteFile.Class(new AbsoluteFolder.Class(folder()()), _name)
                                                    }
                                                def copy(_f : Framework.File.Interface) : Framework.ExistingFile.Interface = 
                                                    {
                                                        FileHandler.copyFile(apply(), _f())
                                                        _f.mustExist
                                                    }
                                                def rename(_x : String) : Framework.ExistingFile.Interface = 
                                                    {
                                                        FileHandler.renameOnDrive(apply(), _x)
                                                        new ExistingFile.Class(folder, _x)
                                                    }
                                                def relativeTo(_x : Framework.AbsoluteFolder.Interface) : Framework.RelativeFile.Interface = new RelativeFile.Class(folder.relativeTo(_x), _name)
                                            }
                                    
                                    def apply(_f : Framework.ExistingFolder.Interface, _x : String) : Interface = new Class(_f, _x) 
                                }
                        object Folder
                            extends Framework.Folder.iFactory
                                {
                                    //import Framework.Folder.{Framework.AbsoluteFolder.Interface => _, _}
                                    
                                    class Class(_name : String)
                                        extends Framework.Folder.Interface
                                            {
                                                override def toString = "Folder(" + _name + ")"
                                                if (!FileHandler.isFolder(_name)) throw new Exception("'" + _name + "' is not a folder.")
                                                def apply() : String = _name
                                                def apply(_x : String) : Framework.FileOrFolder.Interface = new FileOrFolder.Class(_x)
                                                def create() : Framework.ExistingFolder.Interface = 
                                                    {
                                                        FileHandler.create(_name)
                                                        new ExistingFolder.Class(_name)
                                                    }
                                                def mustExist() : Framework.ExistingFolder.Interface = new ExistingFolder.Class(_name)
                                                def mustBeAbsolute() : Framework.AbsoluteFolder.Interface = new AbsoluteFolder.Class(_name)
                                                def mustBeRelative() : Framework.RelativeFolder.Interface = new RelativeFolder.Class(_name)   
                                                def rename(_x : String) : Framework.Folder.Interface = new Folder.Class(FileHandler.rename(_name, _x))
                                            }
                                    
                                    def apply(_x : String) : Framework.Folder.Interface = new Class(_x) 
                                }
                        object AbsoluteFolder
                            extends Framework.AbsoluteFolder.iFactory
                                {
                                    import Framework.AbsoluteFolder._
                                    
                                    class Class(_name : String)
                                        extends Interface
                                            {
                                                override def toString = "AbsoluteFolder(" + _name + ")"
                                                if (!(FileHandler.isFolder(_name) && FileHandler.isFullName(_name))) throw new Exception("'" + _name + "' is not an absolute folder.")
                                                def apply() : String = _name
                                                def apply(_x : String) : Framework.FileOrFolder.Interface = new FileOrFolder.Class(_x)
                                                def rename(_x : String) : Framework.AbsoluteFolder.Interface = new AbsoluteFolder.Class(FileHandler.rename(_name, _x))
                                                def join(_x : Framework.RelativeFolder.Interface) : Framework.AbsoluteFolder.Interface = new AbsoluteFolder.Class(FileHandler.join(_name, _x()))
                                                def join(_x : Framework.RelativeFile.Interface) : Framework.AbsoluteFile.Interface = new AbsoluteFile.Class(join(_x.folder), _x.fileName)//FileOrFolder(FileHandler.join(_name, _x())).mustBeFile.mustBeAbsolute
                                                def mustExist() : Framework.ExistingFolder.Interface = new ExistingFolder.Class(_name)
                                                def relativeTo(_x : Framework.AbsoluteFolder.Interface) : Framework.RelativeFolder.Interface = new RelativeFolder.Class(FileHandler.relativeTo(_name, _x()))
                                            }
                                    
                                    def apply(_x : String) : Interface = new Class(_x) 
                                }
                        object RelativeFolder
                            extends Framework.RelativeFolder.iFactory
                                {
                                    import Framework.RelativeFolder._
                                    
                                    class Class(_name : String)
                                        extends Interface
                                            {
                                                override def toString = "RelativeFolder(" + _name + ")"
                                                if (!(FileHandler.isFolder(_name))) throw new Exception("'" + _name + "' is not a relative folder.")
                                                def apply() : String = _name
                                                def apply(_x : String) : Framework.FileOrFolder.Interface = new FileOrFolder.Class(_x)
                                                def rename(_x : String) : Framework.RelativeFolder.Interface = new RelativeFolder.Class(FileHandler.rename(_name, _x))
                                                def join(_x : Framework.RelativeFolder.Interface) : Framework.RelativeFolder.Interface = new RelativeFolder.Class(FileHandler.join(_name, _x()))
                                                def join(_x : Framework.RelativeFile.Interface) : Framework.RelativeFile.Interface = new RelativeFile.Class(join(_x.folder()), _name)
                                            }
                                    
                                    def apply(_x : String) : Interface = new Class(_x) 
                                }
                        object ExistingFolder
                            extends Framework.ExistingFolder.iFactory
                                {
                                    import Framework.ExistingFolder._
                                    
                                    class Class(_name : String)
                                        extends Interface
                                            {
                                                if (!(FileHandler.isExisting(_name))) throw new Exception("'" + _name + "' is not an existing folder.")
                                                def apply() : String = _name
                                                def apply(_x : String) : Framework.FileOrFolder.Interface = new FileOrFolder.Class(_x)
                                                def delete() : Framework.Folder.Interface = 
                                                    {
                                                        FileHandler.deleteFolder(_name)
                                                        new Folder.Class(_name)
                                                    }
                                                def copy(_f : Framework.Folder.Interface) : Framework.ExistingFolder.Interface = 
                                                    {
                                                        FileHandler.copyFolder(_name, _f())
                                                        new ExistingFolder.Class(_name)
                                                    }
                                                def rename(_x : String) : Framework.ExistingFolder.Interface = 
                                                    {
                                                        val p = FileHandler.renameOnDrive(_name, _x)
                                                        new ExistingFolder.Class(p)
                                                    }
                                                def join(_x : Framework.RelativeFolder.Interface) : Framework.AbsoluteFolder.Interface = new AbsoluteFolder.Class(FileHandler.join(_name, _x()))
                                                def join(_x : Framework.RelativeFile.Interface) : Framework.AbsoluteFile.Interface = new AbsoluteFile.Class(join(_x.folder), _name)
                                                def relativeTo(_x : Framework.AbsoluteFolder.Interface) : Framework.RelativeFolder.Interface = new RelativeFolder.Class(FileHandler.relativeTo(_name, _x()))
                                            }
                                    
                                    def apply(_x : String) : Interface = new Class(_x) 
                                }
                    // Atoms
                        // Utilities
                            // XMLUtils
                                object XMLUtils
                                    extends Framework.XMLUtils.Interface
                                        {
                                            private val valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-:"
                                            def toLabel(_x : String) : String = _x.map({c => if (valid.contains(c)) c.toString else "_" + c.toInt.toString + "_"}).mkString
                                            def fromLabel(_x : String) : String = 
                                                {
                                                    val s = _x.split("_")
                                                    Range(1, s.size, 2)
                                                        .foreach(
                                                            {i =>
                                                                s(i) = s(i).toInt.toChar.toString
                                                            })
                                                    s.mkString
                                                }
                                                
                                            def trim(_x : scala.xml.Node) : scala.xml.Node = 
                                                {
                                                    _x match
                                                        {
                                                            case p : scala.xml.Atom[_] =>
                                                                val n = _x.toString.replace("[\t\r\n]","").trim
                                                                if (n.size == 0) null else new scala.xml.Text(n)
                                                            case p : scala.xml.Elem => 
                                                                p.copy(child = _x.child.map(trim(_)).filter(_ != null))
                                                            case _ =>
                                                                _x
                                                        }
                                                }
                                            def print(_x : scala.xml.Node) : scala.xml.Node = 
                                                {
                                                    def printXML(_indent : String, _y : scala.xml.Node) : List[scala.xml.Node] = 
                                                        {
                                                            _y match
                                                                {
                                                                    case null => 
                                                                        List[scala.xml.Node](new scala.xml.Text(""))
                                                                    case p : scala.xml.Atom[_] =>
                                                                        List[scala.xml.Node](p)
                                                                    case _ =>
                                                                        _y.child.size match
                                                                            {
                                                                                case 0 =>
                                                                                    List[scala.xml.Node](new scala.xml.Text("\n" + _indent), _y)
                                                                                case 1 if (_y.child(0).isInstanceOf[scala.xml.Atom[_]]) =>
                                                                                    List[scala.xml.Node](_y)
                                                                                case _ =>
                                                                                    List[scala.xml.Node]((<p>{new scala.xml.Text("\n")}{
                                                                                            _y.child
                                                                                                .map(
                                                                                                    {n => 
                                                                                                        List[scala.xml.Node](new scala.xml.Text("    " + _indent)) ++ 
                                                                                                            printXML("    " + _indent, n) ++
                                                                                                            List[scala.xml.Node](new scala.xml.Text("\n"))
                                                                                                    })
                                                                                    }{new scala.xml.Text(_indent)}</p>).copy(label = _y.label))
                                                                            }
                                                                }
                                                        }
                                                    val l = printXML("",_x)
                                                    l(l.size - 1)
                                                    //scala.xml.XML.loadString(result.substring(1))
                                                }
                                            def merge(_x : scala.xml.Node, _y : scala.xml.Node) : scala.xml.Node = 
                                                {
                                                    (_x, _y) match
                                                        {
                                                            case (x : scala.xml.Elem, y : scala.xml.Elem) =>
                                                                x.copy(child = x.child ++ y.child, attributes = x.attributes.append(y.attributes))
                                                            case _ =>
                                                                _x
                                                        }
                                                }
                                            def attribute(_xml : scala.xml.Node, _a : String) : Option[String] = _xml.attribute(_a).map(_.toString)
                                            def addAttribute(_xml : scala.xml.Node, _name : String, _content : String) : scala.xml.Node = 
                                                {
                                                    _xml match
                                                        {
                                                            case p : scala.xml.Elem =>
                                                                p.copy(attributes = new scala.xml.UnprefixedAttribute(_name, _content, p.attributes))
                                                            case _ =>
                                                                _xml
                                                        }
                                                }
                                            def extractText(_x : scala.xml.Node) : String = _x.child.cast({case p : scala.xml.Atom[_] => p.text}).foldLeft("")(_ + _)
                                            def label(_xml : scala.xml.Node,_a : String) : scala.xml.Node = 
                                                {
                                                    _xml match
                                                        {
                                                            case p : scala.xml.Elem =>
                                                                p.copy(label = toLabel(_a))
                                                            case _ =>
                                                                _xml
                                                        }
                                                }
                                            def label(_xml : scala.xml.Node) : String = TryCatch({fromLabel(_xml.label)}, "")
                                            def label(_x : String) : String = TryCatch(toLabel(_x), "Unable to convert")
                                        }
                        // Atoms
                            object Atom
                                {
                                    import Framework.Atom._
                                    
                                    abstract class Class[AtomType <: Interface[AtomType]]() 
                                        extends Interface[AtomType]
                                            {
                                            }
                                }
                            object ValueAtom
                                {
                                    import Framework.ValueAtom._
                                    
                                    abstract class Class[AtomType <: Interface[AtomType, JavaType],JavaType](_label : String, _initial : JavaType, _read : String => JavaType, _write : JavaType => String)
                                        extends Atom.Class[AtomType]()
                                        with Interface[AtomType,JavaType]
                                            {
                                                private var __value : JavaType = _initial
                                                def apply() : JavaType = __value
                                                def apply(_x : JavaType) : this.type = Reflect({__value = _x}, this)
                                                def serialize() : Framework.BasicStorageType = serialize(false)
                                                def serialize(_typed : Boolean) : Framework.BasicStorageType = 
                                                    {
                                                        val untyped = XMLUtils.label({<p>{_write(apply())}</p>}, _label)
                                                        if (_typed) XMLUtils.addAttribute(untyped, "Type", _label) else untyped  
                                                    }
                                                def fill(_x : Framework.BasicStorageType) : this.type = Reflect({apply(_read(XMLUtils.extractText(_x)))}, this)
                                                override def toString = _label + ": " + apply()
                                            }
                                }
                            // Instances
                                object TextAtom
                                    extends Framework.TextAtom.iFactory
                                        {
                                            import Framework.TextAtom._
                                            
                                            class Class()
                                                extends ValueAtom.Class[Interface, String]("TextAtom", "", {x => x}, {x => x})
                                                with Interface
                                                    {
                                                        def copy() : Class = new Class()(apply())
                                                    }
                                            def apply(_x : String) : Interface = new Class()(_x)
                                        }
                                object NumberAtom
                                    extends Framework.NumberAtom.iFactory
                                        {
                                            import Framework.NumberAtom._
                                            
                                            class Class()
                                                extends ValueAtom.Class[Interface, Int]("NumberAtom", 0, {_.toInt}, {_.toString}) 
                                                with  Interface
                                                    {
                                                        def copy() : Class = new Class()(apply())
                                                    }
                                                def apply(_x : Int) : Interface = new Class()(_x)
                                        }
                                    
                                object YesNoAtom
                                    extends Framework.YesNoAtom.iFactory
                                        {
                                            import Framework.YesNoAtom._
                                            
                                            class Class()
                                                extends ValueAtom.Class[Interface, Boolean]("YesNoAtom", false, {_ == "true"}, {_.toString}) 
                                                with  Interface
                                                    {
                                                        def copy() : Class = new Class()(apply())
                                                    }
                                                def apply(_x : Boolean) : Interface = new Class()(_x)
                                        }
                
                                    
                                object KeysAtom
                                    extends Framework.KeysAtom.iFactory
                                        {
                                            import Framework.KeysAtom._
                                            
                                            class Class()
                                                extends ValueAtom.Class[Interface, KeySequence.Interface]("KeysAtom", NoModifier, {KeyManager.sequence(_)}, {_.toString}) 
                                                with  Interface
                                                    {
                                                        def copy() : Class = new Class()(apply())
                                                    }
                                                def apply(_x : KeySequence.Interface) : Interface = new Class()(_x)
                                        }
                
                                    
                                //object FileOrFolderAtom
                                //    extends Framework.FileOrFolderAtom.iFactory
                                //        {
                                //            import Framework.FileOrFolderAtom._
                                //            
                                //            class Class()
                                //                extends ValueAtom.Class[Interface, PlainFileOrFolder.Interface]("FileOrFolderAtom", PlainFileOrFolder(""), {PlainFileOrFolder(_)}, {_()}) 
                                //                with  Interface
                                //                    {
                                //                        override def toString = "FileOrFolderAtom: " + apply()().toString
                                //                        def copy() : Class = new Class()(apply())
                                //                    }
                                //            def apply(_x : PlainFileOrFolder.Interface) : Interface = new Class()(_x)
                                //        }
                                object AtomListAtom
                                    extends Framework.AtomListAtom.iFactory
                                        {
                                            import Framework.AtomListAtom._
    
                                            class Constructor[A <: Framework.Atom.Interface[_]](_name : String, _construction : () => A)
                                                extends iConstructor[A]
                                                    {
                                                        def name() : String = _name
                                                        def apply() : A = _construction()
                                                    }
                                            
                                            class Class[A <: Framework.Atom.Interface[_]](_constructors : List[iConstructor[A]])
                                                extends Interface[A]
                                                    {
                                                        private var __value : List[A] = List[A]()
                                                        def apply() : List[A] = __value
                                                        def apply(_x : List[A]) : this.type = Reflect({__value = _x}, this)
                                                        def serialize() : Framework.BasicStorageType = serialize(false)
                                                        def serialize(_typed : Boolean) : Framework.BasicStorageType = <List>{apply().map(_.serialize())}</List>
                                                        def copy() : Class[A] = new Class[A](constructors())(apply())
                                                        def clear() : this.type = Reflect({__value = List[A]()}, this)
                                                        def add[C <: A](_x : C) : C = 
                                                            {
                                                                __value = __value :+ _x
                                                                _x
                                                            }
                                                        def add(_x : A, _y : A, _z : A*) : this.type = Reflect({__value = __value ++ List(_x, _y) ++ _z.toList}, this) 
                                                        def fill(_x : Framework.BasicStorageType) : this.type = 
                                                            Reflect(
                                                                Try(
                                                                    {
                                                                        //System.out.println("Filling with " + _x)
                                                                        clear()
                                                                        _x.child
                                                                            .foreach({n =>
                                                                                    n match
                                                                                        {
                                                                                            case n : scala.xml.Elem =>
                                                                                                construct(n.label)
                                                                                                    .foreach(
                                                                                                        {i => 
                                                                                                            i.fill(n)
                                                                                                            add(i)
                                                                                                        })
                                                                                            case _ =>
                                                                                        }
                                                                                })
                                                                    }), this)
                                                        def size() : Int = __value.size
                                                        private var __constructors : List[iConstructor[A]] = _constructors 
                                                        def constructors() : List[iConstructor[A]] = __constructors
                                                        def constructors(_x : List[iConstructor[A]]) : this.type = Reflect({__constructors = _x}, this)
                                                        def construct(_x : String) : Option[A] = 
                                                            {
                                                                //System.out.println("   trying to construct: " + _x)
                                                                var result : Option[A] = None
                                                                __constructors
                                                                    .filter(_.name() == _x)
                                                                    .foreach({x => result = Some(x())})
                                                                result
                                                            }
                                                        
                                                    }
                                            def apply[A <: Framework.Atom.Interface[_]](_constructors : (String, () => A)*) : Interface[A] = new Class(_constructors.map({x => new Constructor[A](x._1, x._2)}).toList)
                                            def apply[A <: Framework.Atom.Interface[_]](_constructors : List[iConstructor[A]]) : Interface[A] = new Class(_constructors)
                                        }
                                    
                                object StringListAtom
                                    extends Framework.StringListAtom.iFactory
                                        {
                                            import Framework.StringListAtom._
                                            
                                            class Class()
                                                extends Interface
                                                    {
                                                        private var __value : List[String] = List[String]()
                                                        def apply() : List[String] = __value
                                                        def apply(_x : List[String]) : this.type = Reflect({__value = _x}, this)
                                                        
                                                        def serialize() : Framework.BasicStorageType = serialize(false)
                                                        def serialize(_typed : Boolean) : Framework.BasicStorageType = ({<StringList>{apply().map({x => <item>{x}</item>})}</StringList>})                                                    
                                                        
                                                        def fill(_x : Framework.BasicStorageType) : this.type = 
                                                            Reflect(
                                                                Try(
                                                                    {
                                                                        var result = List[String]()
                                                                        _x.child.foreach({n => result = result :+ XMLUtils.extractText(n)})
                                                                        apply(result)
                                                                    }), this)
                                                        override def toString = "StringList(" + apply().mkString(", ") + ")"
                                                        def copy() : Class = new Class()(apply())
                                                        def clear() : this.type = Reflect({apply(List[String]())}, this)
                                                        def add(_x : String*) : this.type = Reflect({apply(apply() ++ _x.toList)}, this)
                                                        def remove(_x : String) : this.type = Reflect({apply(apply().filter(_ != _x))}, this)
                                                        def contains(_x : String) : Boolean = apply().contains(_x)
                                                        def size() : Int = apply().size
                                                    }
                                            def apply() : Interface = new Class()
                                        }
                        // CalculatedValue 
                            object CalculatedValue
                                extends Framework.CalculatedValue.iFactory
                                    {
                                        class Class[A](_f : Long => A, _d : List[Framework.CalculatedValue.Interface[_]])
                                            extends Framework.CalculatedValue.Interface[A]
                                                {
                                                    private var __calculatedStamp : Option[Long] = None
                                                    def calculatedStamp() : Option[Long] = __calculatedStamp 
                                                    private var __checkedStamp : Option[Long] = None
                                                    def checkedStamp() : Option[Long] = __checkedStamp
                                                    def checkedStamp(_x : Long) : Unit = checkedStamp(Some(_x))
                                                    def checkedStamp(_x : Option[Long]) : Unit = __checkedStamp = _x
                                                    private var __calculation : Long => A = _f                                                    
                                                    private var __dependencies : List[Framework.CalculatedValue.Interface[_]] = _d
                                                    //private var __dependencyStamps : List[Option[Long]] = List()                                                    
                                                    def dependencies() : List[Framework.CalculatedValue.Interface[_]] = __dependencies
                                                    def isDirty(_t : Long) : Boolean =
                                                        {
                                                            if (__calculatedStamp == None) return true
                                                            if (_t <= __checkedStamp.getOrElse(-1L)) return false  
                                                            __dependencies.map(_.isDirty(_t)).foldLeft(false)(_ || _)
                                                        }
                                                    private var __value : Option[A] = None
                                                    def apply() : A = apply(System.currentTimeMillis)
                                                    def default(_t : Long, _d : A) : A = value(_t).getOrElse(_d)                                                            
                                                    def apply(_t : Long) : A = value(_t).get 
                                                    def apply(_x : A) : Unit = value({_ => _x})
                                                    private def recalculate(_t : Long) : Unit = 
                                                        {
                                                            val newValue = TryCatch(Some(__calculation(_t)), None)
                                                            if (__value != newValue) 
                                                                {
                                                                    __value = newValue                                                                    
                                                                    __calculatedStamp = Some(System.currentTimeMillis)

                                                                }
                                                        __calculatedStamp = Some(System.currentTimeMillis)
                                                        }
                                                    private def hasCyclicReference(_d : List[Framework.CalculatedValue.Interface[_]]) : Boolean =
                                                        {
                                                            var unchecked = _d
                                                            var checked = List[Framework.CalculatedValue.Interface[_]]()
                                                            var result = false
                                                            while (!result && unchecked.size > 0)
                                                                {
                                                                    val item = unchecked(0)
                                                                    val newItems = 
                                                                        item
                                                                            .dependencies()
                                                                            .filter(!unchecked.contains(_))
                                                                            .filter(!checked.contains(_))
                                                                    if (newItems.contains(this)) result = true 
                                                                    unchecked = (unchecked.drop(1) : List[Framework.CalculatedValue.Interface[_]]) ++ (newItems : List[Framework.CalculatedValue.Interface[_]]) 
                                                                }
                                                            false
                                                        }
                                                    def value(_t : Long) : Option[A] = 
                                                        {
                                                            if (isDirty(_t)) recalculate(_t)
                                                            __checkedStamp = Some(Math.max(__checkedStamp.getOrElse(-1L),_t))
                                                            __value
                                                        }
                                                    def value(_f : Long => A, _d : Framework.CalculatedValue.Interface[_]*) : Unit = 
                                                        {
                                                            if (hasCyclicReference(_d.toList)) throw new Exception("Cyclic reference found.")
                                                            __value = None
                                                            __calculatedStamp = None
                                                            __calculation = _f
                                                            __dependencies = _d.toList
                                                        }
                                                }
                                        def apply[A](_x : A) : Framework.CalculatedValue.Interface[A] = new Class({_ => _x}, List())
                                        def apply[A](_f : Long => A, _d : Framework.CalculatedValue.Interface[_]*) : Framework.CalculatedValue.Interface[A] = new Class(_f, _d.toList)
                                                
                                    }
                    def initialise() = 
                        {
                            Framework.XMLUtils.assign(Implementation.XMLUtils)
                            Framework.KeyManager.assign(Implementation.KeyManager)
                            Framework.FileOrFolder.factory(Implementation.FileOrFolder)
                            Framework.TextAtom.factory(Implementation.TextAtom)
                            Framework.NumberAtom.factory(Implementation.NumberAtom)
                            Framework.YesNoAtom.factory(Implementation.YesNoAtom)
                            Framework.KeysAtom.factory(Implementation.KeysAtom)
                            //Framework.FileOrFolderAtom.factory(Implementation.FileOrFolderAtom)
                            Framework.AtomListAtom.factory(Implementation.AtomListAtom)
                            Framework.StringListAtom.factory(Implementation.StringListAtom)
                            Framework.CalculatedValue.factory(Implementation.CalculatedValue)
                        }
                }
    }
package nl.datakneder.core.Data
    {
        import nl.datakneder.core.Packages._
        import nl.datakneder.core.Utils.Framework._
        
        object Test
            extends Template_TestPackage("Data", 1492773806046L /*CompileDate*/, nl.datakneder.core.Data.Framework)
                {
                    import nl.datakneder.core.Acid.Framework.Acid

                    class KeyManager(KeyManager : Framework.KeyManager.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("KeyManager")
                            {
                                import nl.datakneder.core.Data.Framework.KeyManager
                                
                                def run() : Unit = 
                                    {
                                        // Key Sequence
                                            val a = KeyManager.sequence("A")
                                            val b = KeyManager.sequence("B")
                                            val Shift = KeyManager.sequence("Shift")
                                            val Alt = KeyManager.sequence("Alt")
                                            
                                            Acid("A", a.toString)
                                            Acid("B", b.toString)
                                            Acid("A B", (a + b).toString)
                                            Acid("A C", (a + "C").toString)
                                            Acid("alt shift A", (Shift + Alt + "A").toString)
                                            Acid("alt shift A", (Alt + Shift + "A").toString)
                                            Acid("alt shift A", (Alt + Shift + Alt + "A").toString)
                                            Acid.not(a <= b)
                                            Acid(Shift  <= Shift + "A" + "B" + "C")
                                            Acid(Shift + "A"  <= Shift + "A" + "B" + "C")
                                            Acid(Shift + "A" + "B"  <= Shift + "A" + "B" + "C")
                                            Acid(Shift + "A" + "B" + "C" <= Shift + "A" + "B" + "C")
                                            Acid(Shift + "A" + "B" + "C" == Shift + "A" + "B" + "C")
                                            Acid(Alt + Shift + "A" == Shift + Alt + "A")
                                            Acid((Shift + Alt).toString == (Alt + Shift + "A" + "B" + "C").onlyModifiers().toString)
                                            Acid((a + "BC").toString == (Alt + Shift + "A" + "B" + "C").noModifiers().toString)
                                        
                                            Acid("shift", KeyManager.sequence("shift Pressed SHIFT").toString)
                                            
                                            
                                        // Key Manager
                                            Acid("", KeyManager.currentSequence().toString)
                                            Acid(KeyManager.noKeysDown())
                                            
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, 0, 65,'A'))
                                            Acid.not(KeyManager.noKeysDown())
                                            Acid("A", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, 0, 66,'B'))
                                            Acid("A B", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 17,' '))
                                            Acid("ctrl A B", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 17,'B'))
                                            Acid("ctrl A B", KeyManager.currentSequence().toString)
                                            
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 65,'A'))
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 66,'B'))
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 17,' '))
                                            Acid(KeyManager.noKeysDown())
                                            
                                            KeyManager.clear()
                                            Acid(KeyManager.noKeysDown())
    
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, 0, 65,'A'))
                                            Acid("A", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, 0, 65,'A'))
                                            Acid(KeyManager.noKeysDown())
                                            Acid("A", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, 0, 66,'B'))
                                            Acid("B", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, 0, 66,'B'))
                                            Acid("B", KeyManager.currentSequence().toString)
                                            
    
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.SHIFT_DOWN_MASK, 16,' '))
                                            Acid("shift", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.SHIFT_DOWN_MASK, 65,'A'))
                                            Acid("shift A", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.SHIFT_DOWN_MASK, 65,'A'))
                                            Acid("shift A", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.SHIFT_DOWN_MASK, 66,'B'))
                                            Acid("shift A B", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.SHIFT_DOWN_MASK, 66,'B'))
                                            Acid("shift A B", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.SHIFT_DOWN_MASK, 16,' '))
                                            Acid("shift A B", KeyManager.currentSequence().toString)
                                            Acid(KeyManager.noKeysDown())
    
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 17,' '))
                                            Acid("ctrl", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 65,'A'))
                                            Acid("ctrl A", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 65,'A'))
                                            Acid("ctrl A", KeyManager.currentSequence().toString)
                                            KeyManager.keyDown(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 66,'B'))
                                            Acid("ctrl A B", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 66,'B'))
                                            Acid("ctrl A B", KeyManager.currentSequence().toString)
                                            KeyManager.keyUp(new java.awt.event.KeyEvent(new javax.swing.JLabel(""), 0, 0L, java.awt.event.InputEvent.CTRL_DOWN_MASK, 17,' '))
                                            Acid("ctrl A B", KeyManager.currentSequence().toString)
                                     }
                            }
                    class FileOrFolder(FileOrFolder : Framework.FileOrFolder.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("FileOrFolder")
                            {
                                import nl.datakneder.core.Data.Framework.FileOrFolder
                                
                                def run() : Unit = 
                                    {
                                        import nl.datakneder.core.Utils.Framework._

                                        // FileOrFolder
                                            {
                                                Acid("FileOrFolder(C:\\a.txt)", FileOrFolder("C:\\a.txt").toString)
                                                Acid("FileOrFolder(C:\\b.txt)", FileOrFolder("C:\\a.txt")("C:\\b.txt").toString)
                                                Acid("File(C:\\a.txt)", FileOrFolder("C:\\a.txt").mustBeFile.toString)
                                                Acid("Folder(C:\\A\\)", FileOrFolder("C:\\A\\").mustBeFolder.toString)
                                                Acid.exception(FileOrFolder("C:\\a.txt").mustBeFolder.toString)
                                                Acid.exception(FileOrFolder("C:\\A\\").mustBeFile.toString)   
                                                Acid("C:\\a.txt", FileOrFolder("C:\\a.txt")())
                                            }
                                            
                                        // File
                                            {
                                                Acid("File(C:\\a.txt)", FileOrFolder("C:\\a.txt").mustBeFile.toString)
                                                Acid.exception(FileOrFolder("C:\\A\\").mustBeFile)
                                                Acid("FileOrFolder(C:\\b.txt)", FileOrFolder("C:\\a.txt").mustBeFile()("b.txt").toString)
                                                Acid("C:\\a.txt", FileOrFolder("C:\\a.txt").mustBeFile()())
                                            }
                                            
                                        // Folder
                                            {
                                                Acid("Folder(C:\\A\\B\\)", FileOrFolder("C:\\A\\B\\").mustBeFolder.toString)
                                                Acid.exception(FileOrFolder("C:\\A.txt").mustBeFolder)
                                                Acid("C:\\A\\B\\", FileOrFolder("C:\\A\\B\\").mustBeFolder()())
                                                
                                               
                                                
                                                Acid("FileOrFolder(C:\\C\\D\\)", FileOrFolder("C:\\A\\B\\").mustBeFolder()("C:\\C\\D\\").toString)
                                            }
                                            
                                        // Relative Folder
                                            {
                                                Acid("RelativeFolder(C:\\A\\)", FileOrFolder("C:\\A\\").mustBeFolder.mustBeRelative.toString)    
                                                Acid("RelativeFolder(A\\)", FileOrFolder("A\\").mustBeFolder.mustBeRelative.toString)
                                                Acid.exception(FileOrFolder("C:\\a.txt").mustBeFolder.mustBeRelative)
                                                Acid("C:\\A\\", FileOrFolder("C:\\A\\").mustBeFolder.mustBeRelative()())    
                                                Acid("A\\", FileOrFolder("A\\").mustBeFolder.mustBeRelative()())
                                                Acid("B\\", FileOrFolder("A\\").mustBeFolder.mustBeRelative().rename("B")())
                                                
                                                // Joins
                                                    Acid("C:\\P\\A\\B\\C\\", 
                                                        FileOrFolder("C:\\P\\")
                                                            .mustBeFolder
                                                            .mustBeAbsolute
                                                            .join(
                                                                FileOrFolder("A\\B\\C\\")
                                                                    .mustBeFolder
                                                                    .mustBeRelative())())
                                                    Acid("C:\\P\\Q\\B\\C\\", 
                                                        FileOrFolder("C:\\P\\Q\\R\\")
                                                            .mustBeFolder
                                                            .mustBeAbsolute
                                                            .join(
                                                                FileOrFolder("..\\B\\C\\")
                                                                    .mustBeFolder
                                                                    .mustBeRelative())())
                                                    Acid("A\\B\\C\\P\\Q\\R\\", 
                                                        FileOrFolder("A\\B\\C\\")
                                                            .mustBeFolder
                                                            .mustBeRelative()
                                                            .join(
                                                                FileOrFolder("P\\Q\\R\\")
                                                                    .mustBeFolder
                                                                    .mustBeRelative())())
                                                    Acid("A\\R\\", 
                                                        FileOrFolder("A\\B\\C\\")
                                                            .mustBeFolder
                                                            .mustBeRelative()
                                                            .join(
                                                                FileOrFolder("..\\..\\R\\")
                                                                    .mustBeFolder
                                                                    .mustBeRelative())())
                                            }
                                            
                                        // Absolute Folder
                                            {
                                                Acid("AbsoluteFolder(C:\\A\\)", FileOrFolder("C:\\A\\").mustBeFolder.mustBeAbsolute().toString)    
                                                Acid.exception(FileOrFolder("A\\").mustBeFolder.mustBeAbsolute().toString)
                                                Acid.exception(FileOrFolder("C:\\a.txt").mustBeFolder.mustBeAbsolute())
                                                Acid("C:\\A\\", FileOrFolder("C:\\A\\").mustBeFolder.mustBeAbsolute()())    
                                                Acid("C:\\B\\", FileOrFolder("C:\\A\\").mustBeFolder.mustBeAbsolute().rename("B")())    
                                                
                                                // RelativeTo
                                                    {
                                                        val p = FileOrFolder("C:\\A\\B\\C\\D\\E\\").mustBeFolder.mustBeAbsolute()
                                                        val q = FileOrFolder("C:\\A\\B\\P\\Q\\R\\").mustBeFolder.mustBeAbsolute()
                                                        Acid("..\\..\\..\\C\\D\\E\\", p.relativeTo(q)())
                                                    }
                                            }
                                            
                                        // Existing Folder
                                            {       
                                                Acid.exception(FileOrFolder("C:\\A\\B\\").mustBeFolder.mustExist())
                                                val p = FileHandler.makeFolder(new java.io.File(".").getCanonicalPath) 
                                                Acid(p, FileOrFolder(p).mustBeFolder.mustExist()())
                                                
                                                val q = FileOrFolder("S:\\A\\B\\").mustBeFolder.create
                                                Acid(FileHandler.isExisting("S:\\A\\B\\"))
                                                q.delete()
                                                Acid(!FileHandler.isExisting("S:\\A\\B\\"))
                                                
                                                // Copy
                                                    FileOrFolder("S:\\A\\B\\C\\").mustBeFolder.create
                                                    Acid(FileHandler.isExisting("S:\\A\\B\\C\\"))
                                                    FileOrFolder("S:\\A\\").mustBeFolder.mustExist().copy(FileOrFolder("S:\\P\\").mustBeFolder)
                                                    Acid(FileHandler.isExisting("S:\\P\\B\\C\\"))
                                                    FileOrFolder("S:\\P\\").mustBeFolder.mustExist().delete    
                                                    Acid(!FileHandler.isExisting("S:\\P\\"))
                                                    Acid(FileHandler.isExisting("S:\\A\\B\\C\\"))
                                                    FileOrFolder("S:\\A\\").mustBeFolder.mustExist().delete    
                                                    Acid(!FileHandler.isExisting("S:\\A\\"))
                                                    
                                                    
                                                // Rename
                                                    FileOrFolder("S:\\A\\B\\C\\").mustBeFolder.create
                                                    Acid(FileHandler.isExisting("S:\\A\\B\\C\\"))
                                                    FileOrFolder("S:\\A\\").mustBeFolder.mustExist().rename("P")
                                                    Acid(FileHandler.isExisting("S:\\P\\B\\C\\"))
                                                    Acid(!FileHandler.isExisting("S:\\A\\B\\C\\"))
                                                    FileOrFolder("S:\\P\\").mustBeFolder.mustExist().delete    
                                                    Acid(!FileHandler.isExisting("S:\\P\\"))
                                            }
                                            
                                            
                                     }
                            }
                    class XMLUtils(XMLUtils : Framework.XMLUtils.Interface)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("XMLUtils")
                            {
                                import nl.datakneder.core.Data.Framework.XMLUtils
                                
                                def run() : Unit = 
                                    {
                                        // addAttribute
                                            {
                                                Acid(null, XMLUtils.addAttribute(null, "Type","This"))
                                                Acid(({<p Type="This"></p>}).toString, XMLUtils.addAttribute(<p></p>, "Type","This").toString)
                                            }
                                            
                                        // attribute
                                            {
                                                Acid(Some("This"), XMLUtils.attribute(<p Type="This"></p>, "Type"))
                                                Acid(None, XMLUtils.attribute(<p Type="This"></p>, "Size"))
                                                Acid(None, XMLUtils.attribute(<p></p>, "Type"))
                                            }
                                            
                                        // extractText
                                            {   
                                                Acid("", XMLUtils.extractText(<p></p>))
                                                Acid("A", XMLUtils.extractText(<p>A</p>))
                                                Acid("AB", XMLUtils.extractText(<p>AB</p>))
                                                Acid("AB", XMLUtils.extractText(<p>A<q/>B</p>))
                                            }
                                            
                                        // label
                                            {
                                                Acid("p", XMLUtils.label(<p></p>))
                                                Acid(({<q></q>}).toString, XMLUtils.label(<p></p>, "q").toString)
                                                Acid("Bärbel Claassens", XMLUtils.label(XMLUtils.label(<p></p>, "Bärbel Claassens")))
                                            }
                                            
                                        // print
                                            // Just output. Not tested.
                                                {
                                                    Acid(<p></p>, XMLUtils.print(<p></p>))  
                                                    Acid(<q></q>, XMLUtils.print(<q></q>))  
                                                    var xml = (<p></p>).copy(label="p$q")
                                                    Acid(xml, XMLUtils.print(xml))  
                                                    xml = <p><q>12</q><r><m>x</m><n>y</n></r></p>
                                                    Acid("<p>\n    <q>12</q>\n    <r>\n        <m>x</m>\n        <n>y</n>\n    </r>\n</p>", XMLUtils.print(xml).toString)  
                                                }
                                    }
                            }
                    class TextAtom(TextAtom : Framework.TextAtom.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("TextAtom")
                            {
                                import nl.datakneder.core.Data.Framework.TextAtom
                                
                                def run() : Unit = 
                                    {
                                        //object A extends Framework.TextAtom.Interface
                                        // toString
                                            {
                                                val p = TextAtom("Henk")
                                                Acid("TextAtom: Henk", p.toString)
                                            }
                                            
                                        // copy
                                            {
                                                val p = TextAtom("Henk")
                                                val q = p.copy()
                                                Acid(p.getClass.getName, q.getClass.getName)
                                                Acid("Henk",q())
                                            }
                                            
                                        // fill
                                            {
                                                val p = TextAtom("Henk")
                                                Acid("TextAtom: Henk", p.toString)
                                                p.fill(<q>Harry</q>)
                                                Acid("TextAtom: Harry", p.toString)
                                            }
                                            
                                        // serialize
                                            {
                                                val p = TextAtom("Henk")
                                                Acid("TextAtom: Henk", p.toString)
                                                Acid(({<TextAtom>Henk</TextAtom>}).toString, p.serialize().toString)                                                                                                
                                                Acid(({<TextAtom Type="TextAtom">Henk</TextAtom>}).toString, p.serialize(true).toString)                                                                                                
                                            }
                                            
                                        // apply
                                            {
                                                val p = TextAtom("Henk")
                                                Acid("Henk",p())
                                                p("Harry")
                                                Acid("Harry", p())
                                            }
                                            

                                        
                                        val p = TextAtom("Henk")
                                        Acid("Henk", p())
                                        Acid({<TextAtom>Henk</TextAtom>}, p.serialize())
                                        
                                        val q = p.copy()
                                        Acid(p.getClass.getName, q.getClass.getName)
                                        Acid("Henk", q())
                                        Acid({<TextAtom>Henk</TextAtom>}, q.serialize())                                        
                                        
                                        
                                        p.fill(<TextAtom>Herman</TextAtom>)
                                        Acid("Herman", p())
                                        
                                        val r = p.copy()
                                        Acid(p.getClass.getName, r.getClass.getName)
                                        Acid("Herman", r())
                                        Acid({<TextAtom>Herman</TextAtom>}, r.serialize())                                        
                                        
                                        Acid(p.getClass.getName, q.getClass.getName)
                                        Acid("Henk", q())
                                        Acid({<TextAtom>Henk</TextAtom>}, q.serialize())    
                                        
                                        p("A")
                                        Acid("A", p())
                                    }
                            }
                    class NumberAtom(NumberAtom : Framework.NumberAtom.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("NumberAtom")
                            {
                                import nl.datakneder.core.Data.Framework.NumberAtom
                                
                                def run() : Unit = 
                                    {
                                        //object A extends Framework.NumberAtom.Interface
                                        // toString
                                            {
                                                val p = NumberAtom(12)
                                                Acid("NumberAtom: 12", p.toString)
                                            }
                                            
                                        // copy
                                            {
                                                val p = NumberAtom(12)
                                                val q = p.copy()
                                                Acid(p.getClass.getName, q.getClass.getName)
                                                Acid(12,q())
                                            }
                                            
                                        // fill
                                            {
                                                val p = NumberAtom(12)
                                                Acid("NumberAtom: 12", p.toString)
                                                p.fill(<q>13</q>)
                                                Acid("NumberAtom: 13", p.toString)
                                            }
                                            
                                        // serialize
                                            {
                                                val p = NumberAtom(12)
                                                Acid("NumberAtom: 12", p.toString)
                                                Acid(({<NumberAtom>12</NumberAtom>}).toString, p.serialize().toString)                                                                                                
                                                Acid(({<NumberAtom Type="NumberAtom">12</NumberAtom>}).toString, p.serialize(true).toString)                                                                                                
                                            }
                                            
                                        // apply
                                            {
                                                val p = NumberAtom(12)
                                                Acid(12,p())
                                                p(13)
                                                Acid(13, p())
                                            }
                                    }
                            }
                    class YesNoAtom(YesNoAtom : Framework.YesNoAtom.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("YesNoAtom")
                            {
                                import nl.datakneder.core.Data.Framework.YesNoAtom
                                
                                def run() : Unit = 
                                    {
                                        //object A extends Framework.YesNoAtom.Interface
                                        // toString
                                            {
                                                val p = YesNoAtom(true)
                                                Acid("YesNoAtom: true", p.toString)
                                            }
                                            
                                        // copy
                                            {
                                                val p = YesNoAtom(true)
                                                val q = p.copy()
                                                Acid(p.getClass.getName, q.getClass.getName)
                                                Acid(true,q())
                                            }
                                            
                                        // fill
                                            {
                                                val p = YesNoAtom(true)
                                                Acid("YesNoAtom: true", p.toString)
                                                p.fill(<q>false</q>)
                                                Acid("YesNoAtom: false", p.toString)
                                            }
                                            
                                        // serialize
                                            {
                                                val p = YesNoAtom(true)
                                                Acid("YesNoAtom: true", p.toString)
                                                Acid(({<YesNoAtom>true</YesNoAtom>}).toString, p.serialize().toString)                                                                                                
                                                Acid(({<YesNoAtom Type="YesNoAtom">true</YesNoAtom>}).toString, p.serialize(true).toString)                                                                                                
                                            }
                                            
                                        // apply
                                            {
                                                val p = YesNoAtom(true)
                                                Acid(true,p())
                                                p(false)
                                                Acid(false, p())
                                            }
                                    }
                            }
                    class KeysAtom(KeysAtom : Framework.KeysAtom.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("KeysAtom")
                            {
                                import nl.datakneder.core.Data.Framework.KeysAtom
                                import Framework.KeyDefinitions.CTRL
                                import Framework.KeyDefinitions.ALT
                                
                                def run() : Unit = 
                                    {
                                        //object A extends Framework.KeysAtom.Interface
                                        // toString
                                            {
                                                val p = KeysAtom(CTRL + "P")
                                                Acid("KeysAtom: ctrl P", p.toString)
                                            }
                                            
                                        // copy
                                            {
                                                val p = KeysAtom(CTRL + "P")
                                                val q = p.copy()
                                                Acid(p.getClass.getName, q.getClass.getName)
                                                Acid(CTRL + "P",q())
                                            }
                                            
                                        // fill
                                            {
                                                val p = KeysAtom(CTRL + "P")
                                                Acid("KeysAtom: ctrl P", p.toString)
                                                p.fill(<q>alt ctrl Q</q>)
                                                Acid("KeysAtom: alt ctrl Q", p.toString)
                                            }
                                            
                                        // serialize
                                            {
                                                val p = KeysAtom(CTRL + "P")
                                                Acid("KeysAtom: ctrl P", p.toString)
                                                Acid(({<KeysAtom>ctrl P</KeysAtom>}).toString, p.serialize().toString)                                                                                                
                                                Acid(({<KeysAtom Type="KeysAtom">ctrl P</KeysAtom>}).toString, p.serialize(true).toString)                                                                                                
                                            }
                                            
                                        // apply
                                            {
                                                val p = KeysAtom(CTRL + "P")
                                                Acid(CTRL + "P",p())
                                                p(ALT + CTRL + "Q")
                                                Acid(ALT + CTRL + "Q", p())
                                            }
                                    }
                            }
                    class AtomListAtom(AtomListAtom : Framework.AtomListAtom.iFactory, TextAtom : Framework.TextAtom.iFactory, NumberAtom : Framework.NumberAtom.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("AtomListAtom")
                            {
                                import nl.datakneder.core.Data.Framework.AtomListAtom
                                
                                def run() : Unit = 
                                    {
                                        // Text List
                                            {
                                                val p1 = TextAtom("P1")
                                                val p2 = TextAtom("P2")
                                                val p3 = TextAtom("P3")
                                                
                                                val l = AtomListAtom(("TextAtom", {() => TextAtom("")}))
                                                Acid(0, l().size)
                                                Acid(0, l.size())
                                                Acid(1, l.constructors().size)
                                                Acid(Some(""), l.construct("TextAtom").map(_()))
                                                Acid(None, l.construct("jldjsdas").map(_()))
                                                
                                                Acid(({<List></List>}.toString), l.serialize().toString)
                                                l.add(p1)
                                                Acid(1, l.size())
                                                Acid(({<List><TextAtom>P1</TextAtom></List>}.toString), l.serialize().toString)
                                                l.add(p2)
                                                Acid(2, l.size())
                                                Acid(({<List><TextAtom>P1</TextAtom><TextAtom>P2</TextAtom></List>}.toString), l.serialize().toString)
                                                l.add(p3)
                                                Acid(3, l.size())
                                                Acid(({<List><TextAtom>P1</TextAtom><TextAtom>P2</TextAtom><TextAtom>P3</TextAtom></List>}.toString), l.serialize().toString)
                                                Acid(3, l().size)
                                                
                                                l.fill(<List></List>)
                                                Acid(0, l.size())
                                                l.fill(<List><TextAtom>P1</TextAtom><TextAtom>P2</TextAtom><TextAtom>P3</TextAtom></List>)
                                                Acid(3, l.size())
                                                
                                                Acid(p1, l.add(p1))
                                                Acid(4, l.size)
                                                l.clear
                                                Acid(0, l.size)
                                            }
                                        
                                        // Test Generic List
                                            {
                                                val p1 = TextAtom("P1")
                                                val n2 = NumberAtom(12)
                                                val p3 = TextAtom("P3")
                                                
                                                val l = AtomListAtom(("TextAtom", {() => TextAtom("")}),("NumberAtom", {() => NumberAtom(0)}))
                                                Acid(0, l().size)
                                                Acid(0, l.size())
                                                Acid(2, l.constructors().size)
                                                Acid(Some(""), l.construct("TextAtom").map(_()))
                                                Acid(None, l.construct("jldjsdas").map(_()))
                                                
                                                Acid(({<List></List>}.toString), l.serialize().toString)
                                                l.add(p1)
                                                Acid(1, l.size())
                                                Acid(({<List><TextAtom>P1</TextAtom></List>}.toString), l.serialize().toString)
                                                l.add(n2)
                                                Acid(2, l.size())
                                                Acid(({<List><TextAtom>P1</TextAtom><NumberAtom>12</NumberAtom></List>}.toString), l.serialize().toString)
                                                l.add(p3)
                                                Acid(3, l.size())
                                                Acid(({<List><TextAtom>P1</TextAtom><NumberAtom>12</NumberAtom><TextAtom>P3</TextAtom></List>}.toString), l.serialize().toString)
                                                Acid(3, l().size)
                                                
                                                l.fill(<List></List>)
                                                Acid(0, l.size())
                                                l.fill(<List><TextAtom>P1</TextAtom><NumberAtom>12</NumberAtom><TextAtom>P3</TextAtom></List>)
                                                Acid(3, l.size())
                                                
                                                Acid(p1, l.add(p1))
                                                Acid(4, l.size)
                                                l.clear
                                                Acid(0, l.size)
                                                
                                                Acid(2, l.constructors().size)
                                                l.constructors(l.constructors())
                                                Acid(2, l.constructors().size)
                                            }
                                    }
                            }
                    class StringListAtom(StringListAtom : Framework.StringListAtom.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("StringListAtom")
                            {
                                import nl.datakneder.core.Data.Framework.StringListAtom
                                
                                def run() : Unit = 
                                    {
                                        // toString
                                            {
                                                val p = StringListAtom()
                                                Acid("StringList()", p.toString)
                                                p.add("A","B","C","D")
                                                Acid("StringList(A, B, C, D)", p.toString)                                                
                                            }
                                            
                                        // clear
                                            {
                                                val p = StringListAtom()
                                                Acid("StringList()", p.toString)
                                                p.add("A","B","C","D")
                                                Acid("StringList(A, B, C, D)", p.toString)
                                                p.clear()
                                                Acid("StringList()", p.toString)
                                            }
                                            
                                        // add 
                                            {
                                                val p = StringListAtom()
                                                Acid("StringList()", p.toString)
                                                Acid(0, p.size)
                                                p.add("A","B","C","D")
                                                Acid("StringList(A, B, C, D)", p.toString)
                                            }
                                            
                                        // remove
                                            {
                                                val p = StringListAtom()
                                                Acid("StringList()", p.toString)
                                                p.add("A","B","C","D")
                                                Acid("StringList(A, B, C, D)", p.toString)
                                                p.remove("X")
                                                Acid("StringList(A, B, C, D)", p.toString)
                                                p.remove("C")
                                                Acid("StringList(A, B, D)", p.toString)
                                                p.remove("D")
                                                Acid("StringList(A, B)", p.toString)
                                                p.remove("B").remove("A")
                                                Acid("StringList()", p.toString)
                                            }
                                            
                                        // contains
                                            {
                                                val p = StringListAtom()
                                                p.add("A","B","C","D")
                                                Acid(p.contains("A"))
                                                Acid(p.contains("B"))
                                                Acid(p.contains("C"))
                                                Acid(p.contains("D"))
                                                Acid(!p.contains("E"))
                                                Acid(!p.contains("F"))
                                                Acid(!p.contains("G"))
                                                Acid(!p.contains("H"))
                                            }
                                            
                                        // apply
                                            {
                                                val p = StringListAtom()
                                                p.add("A","B","C","D")
                                                Acid("ABCD", p().mkString)
                                            }
                                            
                                        // size
                                            {
                                                val p = StringListAtom()
                                                Acid(0, p.size())
                                                p.add("A","B","C","D")
                                                Acid(4, p.size())
                                            }
                                            
                                        // copy
                                            {
                                                val p = StringListAtom()
                                                p.add("A","B","C","D")
                                                val q = p.copy()
                                                Acid(p.getClass.getName, q.getClass.getName)
                                                Acid("StringList(A, B, C, D)", q.toString)
                                                p.clear()
                                                Acid("StringList(A, B, C, D)", q.toString)
                                            }
                                            
                                        // fill
                                            {
                                                val p = StringListAtom()
                                                Acid(({<StringList></StringList>}).toString, p.serialize().toString)
                                                p.fill(<StringList><item>A</item><item>B</item><item>C</item><item>D</item></StringList>)
                                                Acid(({<StringList><item>A</item><item>B</item><item>C</item><item>D</item></StringList>}).toString, p.serialize().toString)
                                            }
                                            
                                        // serialize 
                                            {
                                                val p = StringListAtom()
                                                Acid(({<StringList></StringList>}).toString, p.serialize().toString)
                                                p.add("A","B","C","D")
                                                Acid(({<StringList><item>A</item><item>B</item><item>C</item><item>D</item></StringList>}).toString, p.serialize().toString)                                                
                                                // No typed check yet.
                                            }
                                    }
                            }
                    
                    class CalculatedValue(CalculatedValue  : Framework.CalculatedValue.iFactory)
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Calculated Value")
                            {
                                def run() : Unit = 
                                    {
                                        val a = CalculatedValue(1)
                                        var t = System.currentTimeMillis
                                        Acid(a.isDirty(t))
                                        Acid(1, a())
                                        Acid.not(a.isDirty(t))
                                        a(0)
                                        t = System.currentTimeMillis
                                        Acid(a.isDirty(t))
                                        Acid(0, a())
                                        Acid.not(a.isDirty(t))
                                        a(1)
                                        
                                        val b = CalculatedValue(5)
                                        val c = CalculatedValue(6)
                                        val denominator = CalculatedValue({t => 2*a(t)},a)
                                        val discriminant = CalculatedValue({t => b(t)*b(t)-4*a(t)*c(t)},a,b,c)
                                        val numerator = CalculatedValue({t => -b(t) + Math.sqrt(discriminant(t))}, b, discriminant)
                                        val x = CalculatedValue({t => numerator(t)/ denominator(t)}, numerator, denominator)
                                        
                                        Acid(a.isDirty(t))
                                        Acid(b.isDirty(t))
                                        Acid(c.isDirty(t))
                                        Acid(denominator.isDirty(t))
                                        Acid(numerator.isDirty(t))
                                        Acid(discriminant.isDirty(t))
                                        Acid(x.isDirty(t))                             
                                        
                                        val p = discriminant()
                                        t = System.currentTimeMillis
                                        Acid(1, p)
                                        Acid.not(a.isDirty(t))
                                        Acid.not(b.isDirty(t))
                                        Acid.not(c.isDirty(t))
                                        Acid(denominator.isDirty(t))
                                        Acid(numerator.isDirty(t))
                                        Acid.not(discriminant.isDirty(t))
                                        Acid(x.isDirty(t))
                                        Acid(1, a())
                                        Acid(5, b())
                                        Acid(6, c())
                                        Acid(1, discriminant())
                                        
                                        val r = x()
                                        t = System.currentTimeMillis
                                        Acid(1, a())
                                        Acid(5, b())
                                        Acid(6, c())
                                        Acid(1, discriminant())
                                        Acid(2, denominator())
                                        Acid(-2.0, r)
                                        Acid.not(a.isDirty(t))
                                        Acid.not(b.isDirty(t))
                                        Acid.not(c.isDirty(t))
                                        Acid.not(denominator.isDirty(t))
                                        Acid.not(numerator.isDirty(t))
                                        Acid.not(discriminant.isDirty(t))
                                        Acid.not(x.isDirty(t))
                                        
                                        val pp = CalculatedValue({t => 1/a(t)}, a)
                                        t = System.currentTimeMillis
                                        Acid(1, a(t))
                                        Acid(pp.isDirty(t))
                                        Acid(1, pp(t))
                                        Acid.not(pp.isDirty(t))
                                        a(0)
                                        Try(Thread.sleep(100))
                                        t = System.currentTimeMillis
                                        Acid(a.isDirty(t))
                                        Acid(pp.isDirty(t))
                                        Acid.exception(pp(t))
                                        Acid.not(pp.isDirty(t))
                                        
                                        
                                    }
                            }
                }
    }
    