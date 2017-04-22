package nl.datakneder.core.UI.Rock
    {
        import scala.language.reflectiveCalls
        import scala.language.implicitConversions
        
        import nl.datakneder.core.Packages._
        import nl.datakneder.core.Utils.Framework._
        import nl.datakneder.core.Data.Framework.KeySequence
        import nl.datakneder.core.Data.Framework.KeyDefinitions._
            
        object Framework
            extends Template_FrameworkPackage("Rock", 1492890084584L /*CompileDate*/)
                {
                    // Editor
                        //implicit def InjectEdit(_x : Object) =
                        //    new Object
                        //        {
                        //            def edit(_names : String*) : Boolean = 
                        //                {
                        //                    Editor.edit(_x, _names :_*)
                        //                    true
                        //                }
                        //        }
                        object Editor
                            extends Template_Object("Editor")
                                {
                                    trait iConstructionData
                                        {
                                            val owner : Object
                                            val name : String
                                            val read : () => Any
                                            val write : Any => Unit
                                        }
                                    trait Interface
                                        {
                                            def add(_f : Context.Interface[iAddableComponent] => Context.Interface[iAddableComponent] => PartialFunction[Any, iAddableComponent]) : Interface 
                                            def edit(_x : Object, _names : String*) : Boolean 
                                        }
                                    def add(_f : Context.Interface[iAddableComponent] => Context.Interface[iAddableComponent] => PartialFunction[Any, iAddableComponent]) : Interface = interface().add(_f)
                                    def edit(_x : Object, _names : String*) : Boolean = interface().edit(_x, _names :_*)
                                }
                    // Tools
                        object WindowsRefresher
                            {
                                trait Interface
                                    {
                                        def beforeRefresh(_f : () => Unit) : Unit 
                                        def apply() : Unit
                                        def close() : Unit
                                        def refreshRate() : Int
                                        def refreshRate(_x : Int) : this.type
                                    }
                                
                                private var __windowsRefresher : Interface = null
                                def assign(_x : Interface) : Unit = __windowsRefresher = _x
                                
                                def beforeRefresh(_f : () => Unit) : this.type = Reflect(__windowsRefresher.beforeRefresh(_f), this)
                                def start() : this.type = Reflect(__windowsRefresher(), this)
                                def close() : this.type = Reflect(__windowsRefresher.close(), this)
                            }
                        object ThrowExceptionOnNone
                            {
                                def apply[A](_msg : String, _x : Option[() => A]) : A = 
                                    {
                                        if (_x == None) throw new Exception(_msg)
                                        _x.get()
                                    }
                            }
                    // Traits
                        // Core
                            trait iComponent
                                {
                                    def self(_f : this.type => Unit) : this.type                                
                                }
                            trait iProps[A,B]
                                {
                                    def name() : String 
                                    def name(_x : String) : B 
                                    def apply() : A 
                                    def apply(_x : A) : B  
                                    def apply(_f : () => A) : B 
                                    def applyCast(_f : () => Any) : B
                                }
                            trait iUpdatableProps[A, B]
                                extends iProps[A, B]
                                    {
                                        def update(_f : A => Unit) : B
                                        def assign(_x : A) : Unit
                                    }
                            trait iAddableComponent
                                extends iComponent
                                    {
                                        def repaint() : this.type
                                        def component() : java.awt.Component
                                    }
                        // Display
                            trait iCaption
                                {
                                    def caption : iProps[String, this.type]
                                }
                            trait iBackColor
                                {
                                    def backColor : iProps[Option[java.awt.Color], this.type]
                                }
                            trait iEnabled
                                {
                                    def enabled : iProps[Boolean, this.type]
                                }
                            trait iBorder
                                {
                                    def clearBorder() : this.type
                                    def margin(_x : Int) : this.type 
                                    def lineBorder() : this.type 
                                    def lineBorder(_x : Int) : this.type
                                    def lineBorder(_x : java.awt.Color) : this.type
                                    def lineBorder(_x : java.awt.Color, _y : Int) : this.type 
                                    def titleBorder(_x : String) : this.type
                                    def etchedBorder() : this.type
                                }
                            trait iContent[A]
                                {
                                    def content : iUpdatableProps[A, this.type]
                                }
                            trait iPreferredSize
                                {
                                    def width : iProps[Int => Int, this.type]
                                    def addWidthConstraint(_f : Int => Int) : this.type
                                    def minimalWidth(_x : Int) : this.type
                                    def maximalWidth(_x : Int) : this.type
                                    def height : iProps[Int => Int, this.type]
                                    def addHeightConstraint(_f : Int => Int) : this.type
                                    def minimalHeight(_x : Int) : this.type
                                    def maximalHeight(_x : Int) : this.type
                                }
                            trait iHasMenuBar
                                {
                                    def menu : iProps[MenuBar.Interface, this.type]
                                }
                            trait iSimplePanel
                                {
                                    def clear() : this.type
                                    def add(_x : iAddableComponent) : this.type
                                }
                            trait iHasMnemonic
                                {
                                    def mnemonic : iProps[String, this.type]
                                }
                            trait iHasAccelerator
                                {
                                    def accelerator : iProps[KeySequence.Interface, this.type]
                                }
                            trait iHasMargin
                                {
                                    def margin : iProps[Int, this.type]
                                }
                            trait iSliderData
                                {
                                    def defaultMinimum() : Int = 0
                                    def defaultValue() : Int = 0
                                    def defaultMaximum() : Int = 100
                                    
                                    def minimum : iProps[Int, this.type]
                                    def value : iUpdatableProps[Int, this.type]
                                    def maximum : iProps[Int, this.type]
                                }
                            trait iHasList
                                {
                                    def items : iProps[List[String], this.type]
                                }
                            trait iDirection
                                {
                                    def horizontalDirection : iProps[Boolean, this.type]
                                    def horizontal() : this.type
                                    def vertical() : this.type
                                }
                            trait iHasPopup
                                {
                                    def popup : iProps[List[iAddableComponent], this.type]
                                }
                        // Behaviour
                            trait iKeyActionData
                                {
                                    def exactMatch(_k : KeySequence.Interface) : Boolean
                                    def smallerMatch(_k : KeySequence.Interface) : Boolean
                                    def action() : Any
                                }
                            trait iHasKeyActions
                                {
                                    def clearAction(_k : KeySequence.Interface) : this.type
                                    def keyAction(_k : KeySequence.Interface, _a : () => Any) : this.type
                                    def keyAction(_k : KeySequence.Interface => Boolean, _smaller : KeySequence.Interface => Boolean, _a : () => Any) : this.type
                                    def actionList(_k : KeySequence.Interface, _filter : iKeyActionData => Boolean) : List[() => Any] 
                                }
                            trait iClickable
                                {
                                    def click() : this.type
                                    def onClick(_f : () => Unit) : this.type 
                                    def hasClick() : Boolean
                                    def doubleClick() : this.type
                                    def onDoubleClick(_f : () => Unit) : this.type
                                    def hasDoubleClick() : Boolean
                                }
                            trait iFocusable
                                {
                                    def onFocusGained(_f : () => Unit) : this.type
                                    def focusGained() : this.type
                                    def onFocusLost(_f : () => Unit) : this.type
                                    def focusLost() : this.type                                
                                }
                            trait iSplitPanel
                                extends iDirection
                                    {
                                        def sliderPosition : iUpdatableProps[Int, this.type]
                                        //def horizontal : iProps[Boolean, this.type]
                                        //def vertical() : this.type
                                        def left : iProps[iAddableComponent, this.type]
                                        def right : iProps[iAddableComponent, this.type]
                                        def top(_x : iAddableComponent) : this.type
                                        def bottom(_x : iAddableComponent) : this.type
                                    }
                            // Drag And Drop
                                // Drag
                                    trait iInitialiseDragData
                                        {
                                            
                                        }
                                    trait iDragObject
                                        {
                                            
                                        }
                                    trait iCanBeDragged
                                        {
                                            def isDraggable() : Boolean
                                            def dragObject(_x : iInitialiseDragData) : Option[AnyRef]
                                            def onStartDrag(_f : () => Option[AnyRef]) : this.type
                                            def onStartDrag(_f : iInitialiseDragData => Option[AnyRef]) : this.type
                                        }
                                // Drop
                                    trait iDropData
                                        {
                                            def item() : AnyRef
                                            def ctrlPressed() : Boolean
                                            def ctrlShiftPressed() : Boolean
                                        }
                                    trait iCanBeDroppedUpon
                                        {
                                            def canBeDroppedUpon() : Boolean
                                            def canBeDroppedUpon(_x : iDropData) : Boolean
                                            def dropObject(_x : iDropData) : Unit 
                                            def onDrop(_f : iDropData => Unit) : this.type
                                            def dropCondition(_f : iDropData => Boolean) : this.type
                                        }
                        // Abstract 
                            trait iWindow
                                extends iComponent
                                with iCaption
                                with iHasMenuBar
                                    {
                                        def content : iProps[iAddableComponent, this.type]
                                        def add(_x : iAddableComponent) : this.type
                                        def show() : this.type
                                        def close() : this.type
                                    }
                    // Components
                        // Utils
                            abstract class AbstractComponentFactory
                                {
                                    type Interface
                                    val name = getClass.getSimpleName.cutFrom(".",-1).replace("$","")
                                    private var __constructor : Option[() => Interface] = None
                                    def constructor(_x : () => Interface) : this.type = 
                                        {
                                            __constructor = Some(_x)
                                            this
                                        }
                                    def apply() : Interface = ThrowExceptionOnNone(name + " not initialised.", __constructor)
                                    def apply(_f : Interface => Unit) : Interface = 
                                        {
                                            val result = apply()
                                            _f(result)
                                            result
                                        }
                                }
                            trait iFactoryCaption
                                extends AbstractComponentFactory 
                                    {
                                        type Interface <: iCaption
                                        def apply(_x : String) : Interface = apply().caption(_x)
                                        def apply(_f : () => String) : Interface = apply().caption(_f)                                    
                                    }
                            trait iFactoryContent[A]
                                extends AbstractComponentFactory 
                                    {
                                        type Interface <: iContent[A]
    
                                        def apply(_r : () => A) : Interface = apply().content(_r)
                                        def apply(_r : () => A, _w : A => Unit) : Interface = apply().content(_r).content.update(_w)
                                    }
                        // Plain
                            object Label
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iBackColor
                                            with iHasKeyActions 
                                                {
                                                    
                                                }
                                    }
                            object Button
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iPreferredSize
                                            with iHasKeyActions
                                            with iCanBeDragged
                                            with iCanBeDroppedUpon
                                            with iHasPopup
                                                {
                                                    
                                                }
                                    }
                            object KeyButton
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                with iFactoryContent[KeySequence.Interface]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iPreferredSize
                                            with iHasKeyActions
                                            with iContent[KeySequence.Interface]
                                                {
                                                    def defaultContent() : KeySequence.Interface = NoModifier
                                                }
                                    }
                            object CheckBox
                                extends AbstractComponentFactory
                                with iFactoryContent[Boolean]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iContent[Boolean]
                                            with iHasKeyActions 
                                                {
                                                    def defaultContent() : Boolean = false
                                                }
                                    }
                            object TextField
                                extends AbstractComponentFactory
                                with iFactoryContent[String]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iBackColor
                                            with iContent[String]
                                            with iHasKeyActions
                                            with iPreferredSize
                                            with iHasPopup
                                                {
                                                    def defaultContent() : String = ""
                                                }
                                    }
                            object IntField
                                extends AbstractComponentFactory
                                with iFactoryContent[Int]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iBackColor
                                            with iContent[Int]
                                            with iHasKeyActions
                                            with iPreferredSize
                                            with iHasPopup
                                                {
                                                    def defaultContent() : Int = 0
                                                }
                                    }
                            object PasswordField
                                extends AbstractComponentFactory
                                with iFactoryContent[String]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iPreferredSize
                                            with iContent[String]
                                            with iHasKeyActions 
                                            with iHasPopup
                                                {
                                                    def defaultContent() : String = ""                                            
                                                }
                                    }
                            object Slider
                                extends AbstractComponentFactory
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iSliderData
                                            with iHasKeyActions 
                                                {
                                                }
                                    }
                            object Spinner
                                extends AbstractComponentFactory
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iSliderData
                                            with iHasKeyActions 
                                                {
                                                }
                                    }
                            object ColorPicker
                                extends AbstractComponentFactory
                                with iFactoryContent[java.awt.Color]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iContent[java.awt.Color]
                                            with iHasKeyActions 
                                                {
                                                    def defaultContent() : java.awt.Color = java.awt.Color.WHITE                                            
                                                }
                                    }
                            object ComboBox
                                extends AbstractComponentFactory
                                with iFactoryContent[String]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iContent[String]
                                            with iHasKeyActions
                                            with iHasList
                                            with iHasPopup
                                                {
                                                    def selectFirstItem() : this.type
                                                    def defaultContent() : String = ""
                                                }
                                    }
                            object ListBox
                                extends AbstractComponentFactory
                                with iFactoryContent[List[String]]
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iContent[List[String]]
                                            with iHasKeyActions
                                            with iHasPopup
                                            with iHasList
                                                {
                                                    def defaultContent() : List[String] = List[String]()
                                                }
                                    }
                            object ScrollBar
                                extends AbstractComponentFactory
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iSliderData
                                            with iDirection
                                            with iHasKeyActions 
                                                {
                                                }
                                    }
                        // Menus
                            object MenuBar
                                extends AbstractComponentFactory
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iSimplePanel
                                                {
                                                    def actionList(_k : KeySequence.Interface, _filter : iKeyActionData => Boolean) : List[() => Any]
                                                }
                                    }
                            object Menu
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iHasKeyActions
                                            with iSimplePanel
                                            with iHasMnemonic
                                                {
                                                    def actionList(_k : KeySequence.Interface, _filter : iKeyActionData => Boolean) : List[() => Any]
                                                }
                                    }
                            object Separator
                                extends AbstractComponentFactory
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                                {
                                                }
                                    }
                            object MenuItem
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iEnabled
                                            with iClickable
                                            with iFocusable
                                            with iBorder
                                            with iPreferredSize
                                            with iHasKeyActions
                                            with iHasMnemonic
                                            with iHasAccelerator
                                                {
                                                    
                                                }
                                    }
                        // Panels
                            object BorderPanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iPreferredSize
                                            with iHasKeyActions 
                                                {
                                                    def north(_x : iAddableComponent) : this.type
                                                    def west(_x : iAddableComponent) : this.type
                                                    def center(_x : iAddableComponent) : this.type
                                                    def east(_x : iAddableComponent) : this.type
                                                    def south(_x : iAddableComponent) : this.type
                                                }
                                }
                            object ScrollPanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iPreferredSize
                                            with iHasKeyActions 
                                                {
                                                    def add(_x : iAddableComponent) : this.type
                                                }
                                }
                            object TwoColumnPanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iPreferredSize
                                            with iSimplePanel
                                            with iHasKeyActions 
                                                {
                                                    def add(s : String, _x : iAddableComponent) : this.type
                                                }
                                }
                            object DistributedPanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iHasKeyActions
                                            with iSimplePanel
                                            with iDirection
                                                {
                                                }
                                    }
                            object GridPanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iHasMargin
                                            with iHasKeyActions
                                            with iSimplePanel
                                                {
                                                }
                                    }
                            object SplitPanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iPreferredSize
                                            with iHasKeyActions 
                                            with iSplitPanel
                                                {
                                                }
                                    }
                            object QueuePanel
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iAddableComponent 
                                            with iCaption
                                            with iBackColor
                                            with iEnabled
                                            with iClickable
                                            with iBorder
                                            with iHasKeyActions
                                            with iSimplePanel
                                            with iDirection
                                                {
                                                }
                                    }
                        // Frames
                            object Dialog
                                extends AbstractComponentFactory
                                with iFactoryCaption
                                    {
                                        trait Interface
                                            extends iWindow
                                            with iHasKeyActions 
                                                {
                                                    
                                                }
                                    }
                    
                }
            
    }
package nl.datakneder.core.UI.Rock
    {
        import scala.language.reflectiveCalls
        
        import nl.datakneder.core.Packages._
        import nl.datakneder.core.Utils.Framework._
        import nl.datakneder.core.Data.Framework.KeySequence
        import nl.datakneder.core.Data.Framework.KeyManager
        import nl.datakneder.core.Data.Framework.KeyDefinitions._
        
        object Implementation
            extends Template_ImplementationPackage("Rock", 1492890084584L /*CompileDate*/, nl.datakneder.core.UI.Rock.Framework)
                {
                    val Framework = nl.datakneder.core.UI.Rock.Framework
                  
                    // Editor
                        object Editor
                            extends Framework.Editor.Interface
                                {
                                    case class ConstructionData(owner : Object, name : String, read : () => Any, write : Any => Unit)
                                        extends Framework.Editor.iConstructionData
                                    var ComponentContext = Context[nl.datakneder.core.UI.Rock.Framework.iAddableComponent]({_ => Framework.Label("Has no component defined.")})
                                    def add(_f : Context.Interface[Framework.iAddableComponent] => Context.Interface[Framework.iAddableComponent] => PartialFunction[Any, Framework.iAddableComponent]) : Framework.Editor.Interface = 
                                        {
                                            ComponentContext = ComponentContext.add(_f)
                                            this
                                        }
                                    def edit(_x : Object, _names : String*) : Boolean = 
                                        {
                                            import scala.reflect.runtime.{universe => ru}
                                            import scala.reflect.runtime.currentMirror
                                            
                                            val m = ru.runtimeMirror(_x.getClass.getClassLoader)
                                            val im = m.reflect(_x)
                                            
                                            val iterator = 
                                                {
                                                    val list = 
                                                        currentMirror
                                                            .classSymbol((_x).getClass)
                                                            .toType
                                                            .members
                                                            .filter(!_.isMethod)
                                                            .filter(_.isTerm)
                                                            .map(_.asTerm)
                                                            .toList
                                                    if (_names.size != 0)
                                                        {
                                                            _names
                                                                .map(
                                                                    {n => 
                                                                        list
                                                                            .filter(
                                                                                {f =>
                                                                                    //System.out.println("Field: (/%s/, /%s/, /%b/)".format(f.name.toString, n, f.name.toString.equals(n)))
                                                                                    f.name.toString.trim.equals(n)
                                                                                })
                                                                            .take(1)
                                                                            .nextOrElse(null)
                                                                    })
                                                                .filter(_ != null)
                                                                .iterator
                                                        } else 
                                                        {
                                                            list
                                                                .reverseIterator
                                                        }
                                                }
                                            val dialog = Framework.Dialog()
                                            dialog
                                                .keyAction(VK_Enter, 
                                                    {() =>
                                                        dialog.close
                                                    })
                                                .keyAction(VK_Escape, {() => dialog.close})
                                                .add(
                                                    {
                                                        val panel = Framework.TwoColumnPanel()
                                                        
                                                        iterator
                                                            .foreach(
                                                                {f =>
                                                                    Try(
                                                                        {
                                                                            val fieldMirror = im.reflectField(f)
                                                                            //System.out.println("Field: " + f.name.toString)
                                                                            panel.add(f.name.toString, 
                                                                                {
                                                                                    val p = ConstructionData(_x, f.name.toString.trim, {() => fieldMirror.get}, {fieldMirror.set(_)})
                                                                                    val result = ComponentContext((p, fieldMirror.get))
                                                                                    
                                                                                    result
                                                                                })
                                                                        })
                                                                })
                                                        
                                                        Framework.ScrollPanel()
                                                            .add(panel)
                                                    })
                                                .show()
                                            true
                                        }
                                }
                    // Tools            
                        object WindowsRefresher
                            extends Runnable
                            with Framework.WindowsRefresher.Interface 
                                {
                                    private var __beforeRefresh : () => Unit = {() => }
                                    def beforeRefresh(_f : () => Unit) : Unit = __beforeRefresh = _f
                                    var __refreshRate = 1000 / 10
                                    def refreshRate() : Int = __refreshRate
                                    def refreshRate(_x : Int) : this.type = Reflect({__refreshRate = _x}, this)
                                    var cancelled = false
                                    def close() : Unit = 
                                        {
                                            cancelled = true
                                        }
                                    def refresh(_x : Any) : Unit = 
                                        {
                                            __beforeRefresh()
                                            _x match
                                                {
                                                    case p : javax.swing.JDialog with Java.iComponent =>
                                                        p.graphicslessPaint()
                                                        p.getContentPane.getComponents.foreach(refresh(_))
                                                        refresh(p.getJMenuBar)
                                                    case p : javax.swing.JPanel with Java.iComponent =>
                                                        p.graphicslessPaint()
                                                        p.getComponents.foreach(refresh(_))
                                                    case p : javax.swing.JPopupMenu with Java.iComponent =>
                                                        p.graphicslessPaint()
                                                        p.getSubElements.foreach(refresh(_))
                                                    case p : javax.swing.JMenuBar with Java.iComponent =>
                                                        p.graphicslessPaint()
                                                        p.getSubElements.foreach(refresh(_))
                                                    case p : javax.swing.JMenu with Java.iComponent =>
                                                        p.graphicslessPaint()
                                                        p.getMenuComponents.foreach(refresh(_))
                                                    case p : javax.swing.JScrollPane with Java.iComponent =>
                                                        p.graphicslessPaint()
                                                        p.getViewport().getComponents.foreach(refresh(_))
                                                    case p : Java.iComponent =>
                                                        p.graphicslessPaint()
                                                    case p : javax.swing.JComboBox[_] =>
                                                    case p : javax.swing.JList[_] =>
                                                    case p : javax.swing.JButton =>
                                                    case p : javax.swing.JLabel =>
                                                    case p : javax.swing.JCheckBox =>
                                                    case p : javax.swing.JTextField =>
                                                    case p : javax.swing.JDialog =>
                                                    case null => 
                                                    case _ =>
                                                        _x.getClass.getName match
                                                            {
                                                                case "javax.swing.Popup$HeavyWeightWindow" =>
                                                                case "javax.swing.SwingUtilities$SharedOwnerFrame" =>
                                                                case p =>
                                                                    System.out.println("Unknown component: " + p)
                                                            }
                                                }
                                        }
                                    
                                    def run() = 
                                        {
                                            while (!cancelled) 
                                                {
                                                    val time = System.currentTimeMillis
                                                    
                                                    java.awt.Window
                                                        .getWindows()
                                                        .filter(_.isVisible())
                                                        .foreach({w => Try(refresh(w))})
                                                    
                                                    Thread.sleep(Math.max(1, __refreshRate - (System.currentTimeMillis - time)))
                                                }
                                        }
                                    def apply() : Unit = 
                                        {
                                            System.out.println("Starting Thread WindowsRefresher.")
                                            val thread = new Thread(this)
                                            thread.setDaemon(true)
                                            thread.start()
                                        }
                                }
                        object Props
                            {
                                class Class[A, B](_name : String, _read : () => A, _owner : B)
                                    extends Framework.iProps[A, B]
                                        {
                                            private var __name : String = _name
                                            def name() : String = __name
                                            def name(_x : String) : B = 
                                                {
                                                    __name = _x
                                                    _owner  
                                                }
                                            private var __read : () => A = _read
                                            def apply() : A = __read()
                                            def apply(_x : A) : B = apply({() => _x})
                                            def apply(_f : () => A) : B = 
                                                {
                                                    __read = _f
                                                    //System.out.println(name() + " changed to " + __read)
                                                    _owner
                                                }
                                            def applyCast(_f : () => Any) : B = 
                                                {
                                                    apply({() => _f().asInstanceOf[A]})
                                                }
                                        }
                                def apply[A, B](_name : String, _read : () => A, _owner : B) : Framework.iProps[A, B] = new Class(_name, _read, _owner) 
                            }
                        object UpdatableProps
                            {
                                class Class[A,B](_name : String, _read : () => A, _owner : B)
                                    extends Props.Class(_name, _read, _owner)
                                    with Framework.iUpdatableProps[A,B]
                                        {
                                            private var __update : Option[A => Unit] = None
                                            private object __localValue
                                                {
                                                    private var __value : Option[A] = None
                                                    def apply() : Option[A] = __value
                                                    def apply(_f : Option[A]) = __value = _f
                                                }    
                                            def update(_f : A => Unit) : B = 
                                                {
                                                    __update = Some(_f)
                                                    _owner
                                                }
                                            override def apply() : A = __localValue().getOrElse(super.apply())
                                            def assign(_x : A) : Unit =
                                                {
                                                    __update
                                                        .foreach(
                                                            {updateFunction => 
                                                                __localValue(Some(_x))
                                                                updateFunction(_x)
                                                                var counter = 0
                                                                while (counter < 3 && apply() != _x) 
                                                                    {
                                                                        updateFunction(_x)
                                                                        Thread.sleep(5)
                                                                        counter += 1
                                                                    }
                                                                if (counter == 3) System.out.println("Synronisation error for " + _name + ".")
                                                                __localValue(None)
                                                            })
                                                }
                                            def apply(_r : () => A, _w : A => Unit) : B = 
                                                {
                                                    apply(_r)
                                                    update(_w)
                                                }
                                            override def apply(_x : A) : B =
                                                {
                                                    throw new Exception(_name + "used apply(...). \n    Apply should not be used with an updatable Props.")
                                                    _owner
                                                }
                                        }
                                def apply[A, B](_name : String, _read : () => A, _owner : B) : Framework.iUpdatableProps[A, B] = new Class(_name, _read, _owner) 
                                //def apply[A, B](_name : String, _read : () => A, _f : A => Unit, _owner : B) : Framework.iUpdatableProps[A, B] = 
                                //    {
                                //        var result = apply(_name, _read, _owner)
                                //        result.update(_f)
                                //        result
                                //    }
                            }
                        object KeyboardFocusManager
                            extends java.awt.DefaultKeyboardFocusManager
                                {
                                    private var lastFocussedComponent : Option[java.awt.Component] = None 
                                    def isKeyPressed(_e : java.awt.event.KeyEvent) : Boolean = _e.getID() == java.awt.event.KeyEvent.KEY_PRESSED
                                    def isKeyReleased(_e : java.awt.event.KeyEvent) : Boolean = _e.getID() == java.awt.event.KeyEvent.KEY_RELEASED
                                    override def processKeyEvent(_focusedComponent : java.awt.Component, _e : java.awt.event.KeyEvent) =
                                        {
                                            // Consume
                                                (_focusedComponent, _e.getModifiers, _e.getKeyCode) match
                                                    {
                                                        
                                                        case (p : javax.swing.JList[_], 2, 38) =>
                                                            _e.consume
                                                        case (p : javax.swing.JList[_], 2, 40) =>
                                                            _e.consume
                                                        case (p : javax.swing.JList[_], x, y) =>
                                                            System.out.println("Code : " + (x,y))
                                                        case _ =>
                                                    }
                                            
                                            def getList(_f : Framework.iKeyActionData => Boolean) : List[() => Any] = 
                                                {
                                                    var result : List[() => Any] = List()
                                                    var comp : java.awt.Component = _focusedComponent
                                                    //System.out.println("Comp: ")
                                                    while (comp != null) 
                                                        {
                                                            //System.out.println("    " + comp.getClass.getName)
                                                            comp match
                                                                {
                                                                    case _ : javax.swing.JFileChooser => 
                                                                        comp = null
                                                                    case x : Java.iHasKeyActions =>
                                                                        //System.out.println("Component: " + (comp.getClass.getName, x.owner().actionList(KeyManager.currentSequence() , _f).size, KeyManager.currentSequence()))
                                                                        result = result ++ x.owner.actionList(KeyManager.currentSequence() , _f)
                                                                    case _ =>
                                                                }
                                                            if (comp != null) comp = comp.getParent
                                                        }
                                                    //System.out.println("")
                                                    result
                                                }
                                            if (lastFocussedComponent != Some(getFocusOwner))
                                                {
                                                     lastFocussedComponent = Some(getFocusOwner)
                                                     KeyManager.clear()
                                                }
                                            if (isKeyPressed(_e)) 
                                                {
                                                    var addKey = true
                                                    _e.getComponent match
                                                        {
                                                            case p : javax.swing.JComboBox[_] =>
                                                                addKey = !(p.isPopupVisible && List(10,27).contains(_e.getKeyCode))
                                                                //if (!addKey) _e.consume
                                                            case _ =>
                                                        }
                                                    if (addKey)
                                                        {
                                                            KeyManager.keyDown(_e)
                                                            val l = getList({k => k.smallerMatch(KeyManager.currentSequence())})
                                                            //System.out.println(KeyManager.currentSequence.toString + " => " + l.size)
                                                            if (l.size == 1 && getList({k => k.exactMatch(KeyManager.currentSequence())}).size == 1) 
                                                                {
                                                                    l(0)()
                                                                    KeyManager.clearNonModifiers()
                                                                }
                                                        }
                                                }
                                            if (isKeyReleased(_e))
                                                {
                                                    KeyManager.keyUp(_e)
                                                    val l = getList({k => k.smallerMatch(KeyManager.currentSequence())})
                                                    if (l.size == 1 && getList({k => k.exactMatch(KeyManager.currentSequence())}).size == 1) 
                                                        {
                                                            l(0)()
                                                            KeyManager.clearNonModifiers()
                                                        }
                                                    if (KeyManager.noKeysDown() && KeyManager.currentSequence().toString.size > 0) 
                                                        {
                                                            //System.out.println("Keys = '%s'".format(KeyManager.currentSequence().toString))
                                                            getList({k => k.exactMatch(KeyManager.currentSequence())})
                                                                .iterator
                                                                .filter(
                                                                    {d => 
                                                                        d() match
                                                                            {
                                                                                case p : Boolean =>
                                                                                    p
                                                                                case _ =>
                                                                                    true
                                                                            }
                                                                    })
                                                                .take(1)
                                                                .foreach({_ => })
                                                            KeyManager.clear()
                                                        }
                                                }
                                            //System.out.println("Currently: " + KeyManager.currentSequence())
                                            super.processKeyEvent(_focusedComponent, _e)
                                        }
                                }
                                
                    
                    // Implementation
                        object Java
                            {
                                // Core
                                    trait iComponent
                                        {
                                            case class GraphiclessPaintFunction[A](props : Framework.iProps[A, _], function : A => Unit)
                                                {
                                                    //System.out.println("Props: " + (props.name(), props()))
                                                    private var __currentValue : A = props()
                                                    Try(function(__currentValue))
                                                    
                                                    def apply() : Unit = 
                                                        {
                                                            val n = props()
                                                            //if (props.name()=="BackColor") System.out.println("GraphiclessPaint for " + (props.name(), props()))
                                                            if (__currentValue != n)
                                                                {
                                                                    __currentValue = n
                                                                    try 
                                                                        {
                                                                            function(__currentValue)
                                                                        } 
                                                                    catch 
                                                                        {
                                                                            case e : Throwable =>
                                                                                if (n != null) e.printStackTrace
                                                                        }
                                                                }
                                                        }
                                                }
                                            private var __paintList : List[GraphiclessPaintFunction[_]] = List()
                                            def graphicslessPaint() : this.type = 
                                                    {
                                                        __paintList.foreach(
                                                            {f =>
                                                                //if (f.props.name() == "Enabled") System.out.println("Enabled: " + f.props())
                                                                //Try(f())
                                                                f()
                                                            })
                                                        this
                                                    }
                                            protected def addGrapicsFunction[A](_p : Framework.iProps[A, _], _f : A => Unit) : this.type = Reflect(__paintList = __paintList :+ GraphiclessPaintFunction(_p, _f), this)
                                        }
                                // Display
                                    trait iCaption
                                        extends iComponent
                                            {
                                                def owner() : Framework.iCaption
                                                def howToSetCaption(_x : String)
                                                addGrapicsFunction(owner().caption, howToSetCaption(_))
                                            }
                                    trait iBackColor
                                        extends iComponent
                                            {
                                                def owner() : Framework.iBackColor
                                                def howToSetBackColor(_x : Option[java.awt.Color])
                                                addGrapicsFunction(owner().backColor, {(x : Option[java.awt.Color] ) => howToSetBackColor(x)})
                                            }
                                    trait iEnabled
                                        extends iComponent
                                            {
                                                def owner() : Framework.iEnabled
                                                def howToSetEnabled(_x : Boolean)
                                                addGrapicsFunction(owner().enabled, howToSetEnabled(_))
                                            }
                                    trait iBorder
                                        extends iComponent
                                            {
                                                def owner() : Rock.iBorder
                                                def howToSetBorder(_x : javax.swing.border.Border)
                                                addGrapicsFunction(owner().border, howToSetBorder(_))
                                            }
                                    trait iSimplePanel
                                        extends java.awt.Container
                                        with iComponent
                                            {
                                                def owner() : Rock.iSimplePanel
                                                def howToClearComponents()
                                                def howToAddComponent(_x : java.awt.Component)
                                                def howToRepaint() 
                                                addGrapicsFunction(owner().componentList, 
                                                    {(l : List[Framework.iAddableComponent]) =>
                                                        //System.out.println("ComponentList changed.")
                                                        howToClearComponents()
                                                        l.foreach({c => howToAddComponent(c.component())})
                                                        howToRepaint()
                                                    })
                                            }
                                    trait iPreferredSize
                                        extends java.awt.Component
                                        with iComponent
                                            {
                                                def owner() : Framework.iPreferredSize
                                                override def getPreferredSize() : java.awt.Dimension = 
                                                    {
                                                        val r = super.getPreferredSize()
                                                        val width = r.getWidth.toInt
                                                        val height = r.getHeight.toInt
                                                        new java.awt.Dimension(owner().width()(width), owner().height()(height))
                                                    }
                                            }
                                    
                                    trait iHasMenuBar
                                        extends iComponent
                                            {
                                                def owner() : Framework.iHasMenuBar
                                                def howToSetMenuBar(_x : Framework.MenuBar.Interface)
                                                addGrapicsFunction(owner().menu, howToSetMenuBar(_))
                                            }
                                    trait iContent[A]
                                        extends iComponent
                                            {
                                                def owner() : Framework.iContent[A]
                                                def howToSetContent(_x : A)
                                                addGrapicsFunction(owner().content, howToSetContent(_))
                                            }
                                    trait iHasMnemonic
                                        extends iComponent
                                            {
                                                def owner() : Framework.iHasMnemonic
                                                def howToSetMnemonic(_x : String)
                                                addGrapicsFunction(owner().mnemonic, howToSetMnemonic(_))
                                            }
                                    trait iHasAccelerator
                                        extends iComponent
                                            {
                                                def owner() : Framework.iHasAccelerator
                                                def howToSetCaption(_x : String)
                                                addGrapicsFunction(owner().accelerator, {(_ : KeySequence.Interface) => howToSetCaption("")})
                                            }
                                    trait iSliderData
                                        extends iComponent
                                            {
                                                def owner() : Framework.iSliderData
                                                def howToSetMinimum(_x : Int)
                                                def howToSetValue(_x : Int)
                                                def howToSetMaximum(_x : Int)
                                                addGrapicsFunction(owner().minimum, howToSetMinimum(_))
                                                addGrapicsFunction(owner().value, howToSetValue(_))
                                                addGrapicsFunction(owner().maximum, howToSetMaximum(_))
                                            }
                                    trait iHasMargin
                                        extends iComponent
                                            {
                                                def owner() : Framework.iHasMargin
                                                def howToSetMargin(_x : Int)
                                                addGrapicsFunction(owner().margin, howToSetMargin(_))
                                            }
                                    trait iHasList
                                        extends iComponent
                                            {
                                                def owner() : Framework.iHasList
                                                def howToSetItems(_x : List[String])
                                                addGrapicsFunction(owner().items, howToSetItems(_))
                                            }
                                    trait iDirection
                                        extends iComponent
                                            {
                                                def owner() : Framework.iDirection
                                                def howToRepaint()
                                                addGrapicsFunction(owner().horizontalDirection, {(_ : Boolean) => howToRepaint()})
                                            }
                                    trait iHasPopup
                                        extends iComponent
                                            {
                                                def owner() : Framework.iHasPopup
                                                def howToSetPopup()
                                                addGrapicsFunction(owner().popup, {(_ : List[Framework.iAddableComponent]) => howToSetPopup()})
                                            }
                                // Behaviour
                                    trait iHasKeyActions
                                        {
                                            def owner() : Framework.iHasKeyActions
                                        }
                                    trait iClickable
                                        extends iComponent
                                            {
                                                def owner() : Framework.iClickable
                                                object ClickListener
                                                    {
                                                        val doubleClickTime = 400
                                                        var time : Long = 0
                                                        var doubleClickPerformed = false
                                                        def actionPerformed(_source : Object) = 
                                                            {
                                                                //System.out.println("A")
                                                                _source match
                                                                    {
                                                                        case p : Java.iClickable =>
                                                                            //System.out.println("B")
                                                                            if (!p.owner().hasDoubleClick())
                                                                                {
                                                                                    //System.out.println("C")
                                                                                    p.owner().click()
                                                                                } else 
                                                                                    {
                                                                                        //System.out.println("D")
                                                                                        val timeDiff = System.currentTimeMillis - time
                                                                                        if (timeDiff < doubleClickTime)
                                                                                            {
                                                                                                p.owner().doubleClick()
                                                                                                doubleClickPerformed = true
                                                                                            } else
                                                                                                {
                                                                                                    new Thread(
                                                                                                        new Runnable
                                                                                                        {
                                                                                                            def run() = 
                                                                                                                {
                                                                                                                    doubleClickPerformed  = false
                                                                                                                    Thread.sleep(doubleClickTime)
                                                                                                                    if (!doubleClickPerformed) p.owner().click()
                                                                                                                }
                                                                                                        }).start
                                                                                                }
                                                                                    }
                                                                        case _ =>
                                                                    }
                                                                time = System.currentTimeMillis
                                                            }
                                                    }
                                                object ActionClickListener
                                                    extends java.awt.event.ActionListener
                                                        {
                                                            def actionPerformed(_e : java.awt.event.ActionEvent) = 
                                                                {
                                                                    //System.out.println("Action Performed")
                                                                    ClickListener.actionPerformed(_e.getSource)
                                                                }
                                                        }
                                                object MouseClickListener
                                                    extends java.awt.event.MouseAdapter
                                                        {
                                                            override def mousePressed(_e : java.awt.event.MouseEvent) = 
                                                                {
                                                                    //System.out.println("Mouse pressed")
                                                                    ClickListener.actionPerformed(_e.getSource)
                                                                }
                                                        }
                                                
                                                this match
                                                    {
                                                        case p : javax.swing.AbstractButton =>
                                                            //System.out.println("Action Listener Installed")
                                                            p.addActionListener(ActionClickListener)
                                                        case p : java.awt.Component =>
                                                            //System.out.println("Click Listener Installed.")
                                                            p.addMouseListener(MouseClickListener)
                                                        case _ =>
                                                    }
                                            }
                                    trait iFocusable
                                        extends iComponent
                                            {
                                                def owner() : Framework.iFocusable
                                                object FocusListener
                                                    extends java.awt.event.FocusListener
                                                        {
                                                            def focusGained(_e : java.awt.event.FocusEvent) = owner().focusGained()
                                                            def focusLost(_e : java.awt.event.FocusEvent) = owner().focusLost()
                                                        }
                                                def howToSetFocusListener(_x : java.awt.event.FocusListener)
                                                howToSetFocusListener(FocusListener)
                                            }
                                    trait iSplitPanel
                                        extends iComponent
                                        with iDirection
                                            {
                                                def owner() : Framework.iSplitPanel
                                                def howToRepaint()
                                                addGrapicsFunction(owner().sliderPosition, {(_ : Int) => howToRepaint()})
                                                addGrapicsFunction(owner().left, {(_ : Framework.iAddableComponent) => howToRepaint()})
                                                addGrapicsFunction(owner().right, {(_ : Framework.iAddableComponent) => howToRepaint()})
                                            }
                                    // Drag And Drop
                                        // Drag
                                            object DragGestureListener
                                                extends java.awt.dnd.DragGestureListener
                                                    {
                                                        def dragGestureRecognized(dge : java.awt.dnd.DragGestureEvent) = 
                                                            {
                                                                case class AnyTransferable(item : AnyRef) 
                                                                    extends java.awt.datatransfer.Transferable 
                                                                        {
                                                                            val localArrayListFlavor = new java.awt.datatransfer.DataFlavor(java.awt.datatransfer.DataFlavor.javaJVMLocalObjectMimeType + ";class=java.lang.Object")
                                                                            def getTransferData(flavor : java.awt.datatransfer.DataFlavor) = item
                                                                            def getTransferDataFlavors() : Array[java.awt.datatransfer.DataFlavor] = List[java.awt.datatransfer.DataFlavor](localArrayListFlavor).toArray
                                                                            def isDataFlavorSupported(flavor : java.awt.datatransfer.DataFlavor) = localArrayListFlavor.equals(flavor)
                                                                        }
                                                                dge.getComponent match
                                                                    {
                                                                        case component : iCanBeDragged  =>
                                                                            if (component.owner().isDraggable()) 
                                                                                {
                                                                                    val id = Rock.InitialiseData()
                                                                                    val dragObject = component.owner().dragObject(id)
                                                                                    dragObject
                                                                                        .foreach({d => dge.startDrag(null, new AnyTransferable(d), new java.awt.dnd.DragSourceAdapter{})})
                                                                                }
                                                                    }    
                                                            } 
                                                    }
                                            trait iCanBeDragged
                                                {
                                                    def owner() : Framework.iCanBeDragged
                                                    val gestureRecognizer = java.awt.dnd.DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(asInstanceOf[java.awt.Component], java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE, DragGestureListener)
                                                }
                                        // Drop
                                            private object TransferHandler  
                                                extends javax.swing.TransferHandler 
                                                with java.awt.dnd.DropTargetListener
                                                {
                                                    def prepareDrop(info : javax.swing.TransferHandler.TransferSupport) : Option[Framework.iDropData] = 
                                                        {
                                                            val NOTHING_PRESSED = java.awt.dnd.DnDConstants.ACTION_MOVE
                                                            val COPY_PRESSED = java.awt.dnd.DnDConstants.ACTION_COPY
                                                            val WRONG = java.awt.dnd.DnDConstants.ACTION_NONE
                                                            val BOTH_PRESSED = 9
                                                            val x = info.getDropLocation.getDropPoint.getX.intValue
                                                            val y = info.getDropLocation.getDropPoint.getY.intValue
                                                            var dropItem = info.getTransferable.getTransferData(info.getTransferable.getTransferDataFlavors()(0))
                                                            
                                                            info.getDropAction match
                                                                {
                                                                    case NOTHING_PRESSED =>
                                                                        Some(DropData(dropItem, false, false))
                                                                    case COPY_PRESSED =>
                                                                        Some(DropData(dropItem, true, false))
                                                                    case BOTH_PRESSED =>
                                                                        Some(DropData(dropItem, true, true))
                                                                    case WRONG =>
                                                                        info.setDropAction(BOTH_PRESSED)
                                                                        Some(DropData(dropItem, true, true))
                                                                    case x =>
                                                                        System.out.println("Unknown drag action: " + x)
                                                                        None
                                                                }
                                                        }
                                                    
                                                    override def canImport(info : javax.swing.TransferHandler.TransferSupport) : Boolean = 
                                                        {
                                                            info.getComponent match
                                                                {
                                                                    case p : iCanBeDroppedUpon =>
                                                                        prepareDrop(info)
                                                                            .map({q => p.owner().canBeDroppedUpon(q)})
                                                                            .getOrElse(false)
                                                                    case _ =>
                                                                        false
                                                                }
                                                        }                                                                                                                                            
                                                    override def importData(info : javax.swing.TransferHandler.TransferSupport) : Boolean = 
                                                        {
                                                            info.getComponent match
                                                                {
                                                                    case p : iCanBeDroppedUpon =>
                                                                        prepareDrop(info)
                                                                            .foreach({q => p.owner().dropObject(q)})
                                                                        true
                                                                    case _ =>
                                                                        false
                                                                }
                                                        }
                                                    def dragEnter(dtde : java.awt.dnd.DropTargetDragEvent) = {} 
                                                    def dragOver(dtde : java.awt.dnd.DropTargetDragEvent) = {} 
                                                    def dragExit(dtde : java.awt.dnd.DropTargetEvent) = {}
                                                    def dropActionChanged(dtde : java.awt.dnd.DropTargetDragEvent) ={}
                                                    def drop(dtde : java.awt.dnd.DropTargetDropEvent) = {}
                                                }
                                            
                                            object DropData
                                                {
                                                    class Class(_item : AnyRef,  _ctrlPressed : Boolean, _ctrlShiftPressed : Boolean)
                                                        extends Framework.iDropData
                                                            {
                                                                def item() : AnyRef = _item
                                                                def ctrlPressed() : Boolean = _ctrlPressed
                                                                def ctrlShiftPressed() : Boolean = _ctrlShiftPressed
                                                            }
                                                    def apply(_x : AnyRef,  _ctrlPressed : Boolean, _shiftPressed : Boolean) : Framework.iDropData = new Class(_x, _ctrlPressed, _shiftPressed)
                                                }
                                            trait iCanBeDroppedUpon
                                                {
                                                    def owner() : Framework.iCanBeDroppedUpon
                                                    def setTransferHandler(_x : javax.swing.TransferHandler)
                                                    setTransferHandler(TransferHandler)
                                                }
                                // Abstract
                                    trait iWindow
                                        extends iComponent
                                            {
                                                def owner() : Framework.iWindow
                                                def howToAddComponent(_x : java.awt.Component)
                                                addGrapicsFunction(owner().content, {(c : Framework.iAddableComponent) => howToAddComponent(c.component)})
                                            }
                            }
                        object Rock
                            {
                                // Core
                                    trait iComponent
                                        {
                                            def component() : Java.iComponent
                                            def self(_f : this.type => Unit) : this.type = Reflect({_f(this)}, this)
                                            def repaint() : this.type = Reflect({component().graphicslessPaint()}, this)
                                        }
                                // Display
                                    trait iCaption
                                        extends Framework.iCaption
                                            {
                                                def component() : Java.iCaption
                                                val caption : Framework.iProps[String, this.type] = Props[String, this.type]("Caption", {() => ""}, this)  
                                            }
                                    trait iBackColor
                                        extends Framework.iBackColor
                                            {
                                                def component() : Java.iBackColor
                                                val backColor : Framework.iProps[Option[java.awt.Color], this.type] = Props[Option[java.awt.Color], this.type]("BackColor", {() => None}, this)  
                                            }
                                    trait iEnabled
                                        extends Framework.iEnabled
                                            {
                                                def component() : Java.iEnabled
                                                val enabled : Framework.iProps[Boolean, this.type] = Props[Boolean, this.type]("Enabled", {() => true}, this)  
                                            }
                                    trait iBorder
                                        extends Framework.iBorder
                                            {
                                                def component() : Java.iBorder
                                                val border : Framework.iProps[javax.swing.border.Border, this.type] = Props[javax.swing.border.Border, this.type]("Border", {() => null}, this)
                                                private def addBorder(_x : javax.swing.border.Border) : this.type = 
                                                    {
                                                        border() match 
                                                            {
                                                                case null =>
                                                                    border(_x)
                                                                case x =>
                                                                    border(javax.swing.BorderFactory.createCompoundBorder(_x, x))
                                                            }
                                                    }
                                                def clearBorder() : this.type = Reflect({border({() => null})}, this)
                                                def margin(_x : Int) : this.type = addBorder(javax.swing.BorderFactory.createEmptyBorder(_x, _x, _x, _x))
                                                def lineBorder() : this.type = lineBorder(java.awt.Color.BLACK, 1)
                                                def lineBorder(_x : Int) : this.type = lineBorder(java.awt.Color.BLACK, _x)
                                                def lineBorder(_x : java.awt.Color) : this.type = lineBorder(_x, 1)
                                                def lineBorder(_x : java.awt.Color, _y : Int) : this.type = addBorder(javax.swing.BorderFactory.createLineBorder(_x, _y))
                                                def titleBorder(_x : String) : this.type = addBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), _x))
                                                def etchedBorder() : this.type = addBorder(javax.swing.BorderFactory.createEtchedBorder())
                                            }
                                    trait iPreferredSize
                                        extends Framework.iPreferredSize
                                            {
                                                def component() : Java.iPreferredSize
                                                
                                                val width : Framework.iProps[Int => Int, this.type] = Props[Int => Int, this.type]("Caption", {() => {i => i}}, this)  
                                                def addWidthConstraint(_f : Int => Int) : this.type =
                                                        {
                                                            val currentFunction = width()
                                                            width({i => _f(currentFunction(i))})
                                                        }
                                                def minimalWidth(_x : Int) : this.type = addWidthConstraint({i => Math.max(_x, i)})
                                                def maximalWidth(_x : Int) : this.type = addWidthConstraint({i => Math.min(_x, i)})
                                                val height : Framework.iProps[Int => Int, this.type] = Props[Int => Int, this.type]("Caption", {() => {i => i}}, this)  
                                                def addHeightConstraint(_f : Int => Int) : this.type =
                                                        {
                                                            val currentFunction = height()
                                                            height({i => _f(currentFunction(i))})
                                                        }
                                                def minimalHeight(_x : Int) : this.type = addHeightConstraint({i => Math.max(_x, i)})
                                                def maximalHeight(_x : Int) : this.type = addHeightConstraint({i => Math.min(_x, i)})
                                            }
                                    trait iSimplePanel
                                        extends Framework.iSimplePanel
                                            {
                                                def component() : Java.iSimplePanel
                                                val componentList : Framework.iProps[List[Framework.iAddableComponent], this.type] = Props[List[Framework.iAddableComponent], this.type]("ComponentList", {() => List[Framework.iAddableComponent]()}, this)                                              
                                                def add(_x : Framework.iAddableComponent) : this.type = Reflect({componentList(componentList() :+ _x)}, this)
                                                def clear() : this.type = Reflect({componentList(List[Framework.iAddableComponent]())}, this)
                                            }
                                    trait iHasMenuBar
                                        extends Framework.iHasMenuBar
                                            {
                                                def component() : Java.iHasMenuBar
                                                val menu : Framework.iProps[Framework.MenuBar.Interface, this.type] = Props[Framework.MenuBar.Interface, this.type]("MenuBar", {() => null}, this)  
                                            }
                                    trait iContent[A]
                                        extends Framework.iContent[A]
                                            {
                                                def component() : Java.iContent[A]
                                                def defaultContent() : A
                                                val content : Framework.iUpdatableProps[A, this.type] = UpdatableProps[A, this.type]("content", {() => defaultContent()}, this)  
                                            }
                                    trait iHasMnemonic
                                        extends Framework.iHasMnemonic
                                            {
                                                def component() : Java.iHasMnemonic
                                                val mnemonic : Framework.iProps[String, this.type] = Props[String, this.type]("Mnemonic", {() => ""}, this)  
                                            }
                                    trait iHasAccelerator
                                        extends Framework.iHasAccelerator
                                            {
                                                def component() : Java.iHasAccelerator
                                                val accelerator : Framework.iProps[KeySequence.Interface, this.type] = Props[KeySequence.Interface, this.type]("Accelerator", {() => NoModifier}, this)  
                                            }
                                    trait iSliderData
                                        extends Framework.iSliderData
                                            {
                                                def component() : Java.iSliderData
                                                val minimum : Framework.iProps[Int, this.type] = Props[Int, this.type]("Minimum", {() => defaultMinimum()}, this)  
                                                val value : Framework.iUpdatableProps[Int, this.type] = UpdatableProps[Int, this.type]("Value", {() => defaultValue()}, this)  
                                                val maximum : Framework.iProps[Int, this.type] = Props[Int, this.type]("Maximum", {() => defaultMaximum()}, this)  
                                            }
                                    trait iHasMargin
                                        extends Framework.iHasMargin
                                            {
                                                def component() : Java.iHasMargin
                                                val margin : Framework.iProps[Int, this.type] = Props[Int, this.type]("Margin", {() => 0}, this)  
                                            }
                                    trait iHasList
                                        extends Framework.iHasList
                                            {
                                                def component() : Java.iHasList
                                                val items : Framework.iProps[List[String], this.type] = Props[List[String], this.type]("Caption", {() => List[String]()}, this)  
                                            }
                                    trait iDirection
                                        extends Framework.iDirection
                                            {
                                                def component() : Java.iDirection
                                                val horizontalDirection : Framework.iProps[Boolean, this.type] = Props[Boolean, this.type]("HorizontalDirection", {() => true}, this)
                                                def horizontal() : this.type = horizontalDirection(true)
                                                def vertical() : this.type = horizontalDirection(false)
                                            }
                                    trait iHasPopup
                                        extends Framework.iHasPopup
                                            {
                                                def component() : Java.iHasPopup
                                                val popup : Framework.iProps[List[Framework.iAddableComponent], this.type] = Props[List[Framework.iAddableComponent], this.type]("Popup", {() => List[Framework.iAddableComponent]()}, this)  
                                            }
                                // Behaviour
                                    trait iHasKeyActions
                                        {
                                            class KeySequenceData(_exactMatch : KeySequence.Interface => Boolean, _smallerMatch : KeySequence.Interface => Boolean, _action : () => Any)
                                                extends Framework.iKeyActionData
                                                    {
                                                        def exactMatch(_k : KeySequence.Interface) : Boolean = _exactMatch(_k)
                                                        def smallerMatch(_k : KeySequence.Interface) : Boolean = _smallerMatch(_k)
                                                        def action() : Any = _action()
                                                    }
                                          
                                            def component() : Java.iHasKeyActions
                                            private var __list : List[Framework.iKeyActionData] = List()
                                            def clearAction(_k : KeySequence.Interface) : this.type = Reflect(__list = __list.filter(!_.exactMatch(_k)), this)
                                            def keyAction(_k : KeySequence.Interface, _a : () => Any) : this.type = keyAction({_ == _k}, {_ <= _k}, _a)
                                            def keyAction(_k : KeySequence.Interface => Boolean, _smaller : KeySequence.Interface => Boolean, _a : () => Any) : this.type = Reflect({__list = __list :+ (new KeySequenceData(_k, _smaller, _a))}, this)
                                            def actionList(_k : KeySequence.Interface, _filter : Framework.iKeyActionData => Boolean) : List[() => Any] = 
                                                {
                                                    //System.out.println("Key Action List: " + (__list.size, __list.filter({a => _filter(a)}).size))
                                                    var result = __list.filter({a => _filter(a)}).map({n => {() => n.action()}})
                                                    this match
                                                        {
                                                            case p : Rock.iHasPopup =>
                                                                p.popup()
                                                                    .foreach(
                                                                        {p => 
                                                                            p match
                                                                                {
                                                                                    case q : iHasKeyActions =>
                                                                                        result = result ++ q.actionList(_k, _filter)
                                                                                    case _ =>
                                                                                }
                                                                        })
                                                                result
                                                            case _ =>
                                                                result
                                                        }
                                                }
                                            
                                        }
                                    trait iClickable
                                        extends Framework.iClickable
                                            {
                                                def component() : Java.iClickable
                                                val onClickFunction : Framework.iProps[Option[() => Unit], this.type] = Props[Option[() => Unit], this.type]("onClick", {() => None}, this)  
                                                val onDoubleClickFunction : Framework.iProps[Option[() => Unit], this.type] = Props[Option[() => Unit], this.type]("onDoubleClick", {() => None}, this)
                                                
                                                def click() : this.type = Reflect(onClickFunction().foreach(_()), this)
                                                def onClick(_f : () => Unit) : this.type = onClickFunction(Some(_f))
                                                def hasClick() : Boolean = onClickFunction() != None
                                                def doubleClick() : this.type = Reflect(onDoubleClickFunction().foreach(_()), this)
                                                def onDoubleClick(_f : () => Unit) : this.type = onDoubleClickFunction(Some(_f))
                                                def hasDoubleClick() : Boolean = onDoubleClickFunction() != None
                                            }
                                    trait iFocusable
                                        extends Framework.iFocusable
                                            {
                                                def component() : Java.iFocusable
                                                val onFocusGainedFunction : Framework.iProps[Option[() => Unit], this.type] = Props[Option[() => Unit], this.type]("onFocusGained", {() => None}, this)  
                                                val onFocusLostFunction : Framework.iProps[Option[() => Unit], this.type] = Props[Option[() => Unit], this.type]("onFocusLost", {() => None}, this)
                                                
                                                def onFocusGained(_f : () => Unit) : this.type = onFocusGainedFunction(Some(_f))
                                                def focusGained() : this.type = Reflect({onFocusGainedFunction().foreach(_())}, this)
                                                def onFocusLost(_f : () => Unit) : this.type = onFocusLostFunction(Some(_f))
                                                def focusLost() : this.type = Reflect({onFocusLostFunction().foreach(_())}, this)                                
                                            }
                                    trait iSplitPanel
                                        extends Framework.iSplitPanel
                                        with iDirection
                                            {
                                                def component() : Java.iSplitPanel
                                                val sliderPosition : Framework.iUpdatableProps[Int, this.type] = UpdatableProps[Int, this.type]("SliderPosition", {() => 100}, this)  
                                                val left : Framework.iProps[Framework.iAddableComponent, this.type] = Props[Framework.iAddableComponent, this.type]("Left", {() => null}, this)  
                                                val right : Framework.iProps[Framework.iAddableComponent, this.type] = Props[Framework.iAddableComponent, this.type]("Right", {() => null}, this)  
                                                def top(_x : Framework.iAddableComponent) : this.type = left(_x)
                                                def bottom(_x : Framework.iAddableComponent) : this.type = right(_x)
                                            }
                                    // Drag And Drop
                                        // Drag
                                            object InitialiseData
                                                {
                                                    class Class()
                                                        extends Framework.iInitialiseDragData
                                                            {
                                                                
                                                            }
                                                    def apply() : Framework.iInitialiseDragData = new Class()
                                                }
                                            trait iCanBeDragged
                                                {
                                                    private var __onStartDrag : Option[Framework.iInitialiseDragData => Option[AnyRef]] = None
                                                    def isDraggable() : Boolean = __onStartDrag != None
                                                    def dragObject(_x : Framework.iInitialiseDragData) : Option[AnyRef] = __onStartDrag.flatMap(_(_x))
                                                    def onStartDrag(_f : () => Option[AnyRef]) : this.type = onStartDrag({_ => _f()})
                                                    def onStartDrag(_f : Framework.iInitialiseDragData => Option[AnyRef]) : this.type = Reflect({__onStartDrag = Some(_f)}, this)
                                                }
                                        // Drop
                                            trait iCanBeDroppedUpon
                                                {
                                                    private var __onDrop : Option[Framework.iDropData => Unit] = None
                                                    private var __dropCondition : Option[Framework.iDropData => Boolean] = None
                                                    def canBeDroppedUpon() : Boolean = __onDrop != None
                                                    def canBeDroppedUpon(_x : Framework.iDropData) : Boolean = __dropCondition.map(_(_x)).getOrElse(false)
                                                    def dropObject(_x : Framework.iDropData) : Unit =  __onDrop.foreach({f => f(_x)})
                                                    def onDrop(_f : Framework.iDropData => Unit) : this.type = Reflect({__onDrop = Some(_f)}, this)
                                                    def dropCondition(_f : Framework.iDropData => Boolean) : this.type = Reflect({__dropCondition = Some(_f)}, this)
                                                }
        
                                // Abstract
                                    trait iWindow
                                        {
                                            val content : Framework.iProps[Framework.iAddableComponent, this.type] = Props[Framework.iAddableComponent, this.type]("Content", {() => null}, this)
                                            def add(_x : Framework.iAddableComponent) : this.type = content(_x)
                                        }
                            }
                        
                    // Objects
                        // Plain
                            class Label()
                                extends Framework.Label.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JLabel
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = setText(_x)
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                        }
                                        def component() = __component
                                    }
                            class CheckBox()
                                extends Framework.CheckBox.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iContent[Boolean]
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JCheckBox("      ")
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iContent[Boolean]
                                                        {
                                                            def owner() = __owner
                                                            def howToSetContent(_x : Boolean) = setSelected(_x)
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            
                                                            addChangeListener(
                                                                new javax.swing.event.ChangeListener
                                                                    {
                                                                        override def stateChanged(_e : javax.swing.event.ChangeEvent) = content.assign(isSelected)
                                                                    })
                                                            
                                                            setHorizontalTextPosition(javax.swing.SwingConstants.CENTER)
                                                        }
                                        def component() = __component
                                        margin(5)
                                    }
                            class TextField()
                                extends Framework.TextField.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBackColor
                                with Rock.iBorder
                                with Rock.iPreferredSize
                                with Rock.iFocusable
                                with Rock.iHasPopup
                                with Rock.iContent[String]
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JTextField()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iPreferredSize
                                                    with Java.iBackColor
                                                    with Java.iFocusable
                                                    with Java.iHasPopup
                                                    with Java.iContent[String]
                                                        {
                                                            if (backColor() == None) backColor(Some(java.awt.Color.WHITE))

                                                            def owner() = __owner
                                                            def howToSetContent(_x : String) =
                                                                {
                                                                    val v = if (_x == null) "" else _x
                                                                    if (getText() != v) setText(v)
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetPopup() = 
                                                            {
                                                                if (popup().size > 0)  
                                                                    {
                                                                        val p = new javax.swing.JPopupMenu
                                                                        popup().foreach({q => p.add(q.component())})
                                                                        setComponentPopupMenu(p)
                                                                    } else setComponentPopupMenu(null) 
                                                            }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    //System.out.println("A")
                                                                    //System.out.println("A(%s)".format(_x.toString))
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                            
                                                            getDocument().addDocumentListener(
                                                                new javax.swing.event.DocumentListener
                                                                    {
                                                                        def changed() = content.assign(getText)
                                                                        def changedUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                        def insertUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                        def removeUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                    })
                                                        }
                                        def component() = __component
                                        onFocusGained({() => __component.selectAll()})
                                        border(new javax.swing.JTextField().getBorder)
                                    }
                            class IntField()
                                extends Framework.IntField.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBackColor
                                with Rock.iBorder
                                with Rock.iPreferredSize
                                with Rock.iFocusable
                                with Rock.iHasPopup
                                with Rock.iContent[Int]
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JTextField()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iPreferredSize
                                                    with Java.iBackColor
                                                    with Java.iFocusable
                                                    with Java.iHasPopup
                                                    with Java.iContent[Int]
                                                        {
                                                            if (backColor() == None) backColor(Some(java.awt.Color.WHITE))

                                                            def owner() = __owner
                                                            def howToSetContent(_x : Int) =
                                                                {
                                                                    val v = "" + _x
                                                                    if (getText() != v) setText(v)
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetPopup() = 
                                                            {
                                                                if (popup().size > 0)  
                                                                    {
                                                                        val p = new javax.swing.JPopupMenu
                                                                        popup().foreach({q => p.add(q.component())})
                                                                        setComponentPopupMenu(p)
                                                                    } else setComponentPopupMenu(null) 
                                                            }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    //System.out.println("A(%s)".format(_x.toString))
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                            
                                                            getDocument().addDocumentListener(
                                                                new javax.swing.event.DocumentListener
                                                                    {
                                                                        def changed() = Try(content.assign(getText.toInt))
                                                                        def changedUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                        def insertUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                        def removeUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                    })
                                                        }
                                        def component() = __component
                                        onFocusGained({() => __component.selectAll()})
                                        border(new javax.swing.JTextField().getBorder)
                                    }
                            class PasswordField()
                                extends Framework.PasswordField.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iPreferredSize
                                with Rock.iHasPopup
                                with Rock.iContent[String]
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JPasswordField()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iHasPopup
                                                    with Java.iPreferredSize
                                                    with Java.iContent[String]
                                                        {
                                                            def owner() = __owner
                                                            def howToSetContent(_x : String) =
                                                                {
                                                                    val v = if (_x == null) "" else _x
                                                                    if (getPassword().mkString != v) setText(v)
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetPopup() = 
                                                                {
                                                                    if (popup().size > 0)  
                                                                        {
                                                                            val p = new javax.swing.JPopupMenu
                                                                            popup().foreach({q => p.add(q.component())})
                                                                            setComponentPopupMenu(p)
                                                                        } else setComponentPopupMenu(null) 
                                                                }
    
                                                            getDocument().addDocumentListener(
                                                                new javax.swing.event.DocumentListener
                                                                    {
                                                                        def changed() = content.assign(getPassword().mkString)
                                                                        def changedUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                        def insertUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                        def removeUpdate(_e : javax.swing.event.DocumentEvent) = changed()
                                                                    })
                                                            setBorder(new javax.swing.JTextField().getBorder)
                                                        }
                                        def component() = __component
                                        onFocusGained({() => __component.selectAll()})
                                    }
                            class Button()
                                extends Framework.Button.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iFocusable
                                with Rock.iPreferredSize
                                with Rock.iBorder
                                with Rock.iHasPopup
                                with Rock.iCanBeDragged
                                with Rock.iCanBeDroppedUpon
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JButton
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iPreferredSize
                                                    with Java.iFocusable
                                                    with Java.iBorder
                                                    with Java.iHasPopup
                                                    with Java.iCanBeDragged
                                                    with Java.iCanBeDroppedUpon
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = setText(_x)
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetPopup() = 
                                                            {
                                                                if (popup().size > 0)  
                                                                    {
                                                                        val p = new javax.swing.JPopupMenu
                                                                        popup().foreach({q => p.add(q.component())})
                                                                        setComponentPopupMenu(p)
                                                                    } else setComponentPopupMenu(null) 
                                                            }
                                                        }
                                        def component() = __component
                                        border(new javax.swing.JButton().getBorder())
                                    }
                            
                            class KeyButton()
                                extends Framework.KeyButton.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iFocusable
                                with Rock.iPreferredSize
                                with Rock.iBorder
                                with Rock.iContent[KeySequence.Interface]
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JButton
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iPreferredSize
                                                    with Java.iFocusable
                                                    with Java.iBorder
                                                    with Java.iContent[KeySequence.Interface]
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = setText(_x)
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetContent(_x : KeySequence.Interface) = setText(" " + _x)
                                                        }
                                        def component() = __component
                                        border(new javax.swing.JButton().getBorder())
                                        keyAction({(x : KeySequence.Interface) => false},{(x : KeySequence.Interface) => content.assign(x);false}, {() => } )
                                        onClick({() => content.assign(NoModifier)})
                                    }
                            
                            class Slider()
                                extends Framework.Slider.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iSliderData
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JSlider()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iSliderData
                                                        {
                                                            def owner() = __owner
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetMinimum(_x : Int) = setMinimum(_x)
                                                            def howToSetValue(_x : Int) = setValue(_x)
                                                            def howToSetMaximum(_x : Int) = setMaximum(_x)
                                                            
                                                            addChangeListener(
                                                                new javax.swing.event.ChangeListener
                                                                    {
                                                                        override def stateChanged(_e : javax.swing.event.ChangeEvent) = value.assign(getValue)
                                                                    })
    
                                                        }
                                        def component() = __component
                                    }
                            class Spinner()
                                extends Framework.Spinner.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iSliderData
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JSpinner()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iSliderData
                                                        {
                                                            val model = new javax.swing.SpinnerNumberModel()
                                                            setModel(model)
                                                            def owner() = __owner
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetMinimum(_x : Int) = model.setMinimum(_x)
                                                            def howToSetValue(_x : Int) = setValue(_x)
                                                            def howToSetMaximum(_x : Int) = model.setMaximum(_x)
                                                            
                                                            howToSetValue(owner().value())
                                                            
                                                            addChangeListener(
                                                                new javax.swing.event.ChangeListener
                                                                    {
                                                                        override def stateChanged(_e : javax.swing.event.ChangeEvent) = value.assign(model.getNumber.intValue)
                                                                    })
    
                                                        }
                                        def component() = __component
                                    }
                            class ColorPicker()
                                extends Framework.ColorPicker.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iContent[java.awt.Color]
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JColorChooser()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iContent[java.awt.Color]
                                                        {
                                                            def owner() = __owner
                                                            def howToSetContent(_x : java.awt.Color) =
                                                                {
                                                                    val v = if (_x == null) java.awt.Color.WHITE else _x
                                                                    if (getColor() != v) setColor(v)
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            getSelectionModel()
                                                                .addChangeListener(
                                                                    new javax.swing.event.ChangeListener
                                                                        {
                                                                            override def stateChanged(_e : javax.swing.event.ChangeEvent) = content.assign(getColor)
                                                                        })
                                                        }
                                        def component() = __component
                                        border(new javax.swing.JColorChooser().getBorder)
                                    }
                            class ComboBox()
                                extends Framework.ComboBox.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iContent[String]
                                with Rock.iHasList
                                with Rock.iPreferredSize
                                with Rock.iHasPopup
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JComboBox[String]()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iContent[String]
                                                    with Java.iHasList
                                                    with Java.iHasPopup
                                                    with Java.iPreferredSize
                                                        {
                                                            def owner() = __owner
                                                            def howToSetContent(_x : String) =
                                                                {
                                                                    val v = if (_x == null) "" else _x
                                                                    val newIndex = items().indexOf(v)
                                                                    setSelectedIndex(newIndex)
                                                                    //System.out.println("Replace " + getSelectedIndex + " with " + newIndex)
                                                                    if (getSelectedIndex() != newIndex) System.out.println("Illegal selected Index.")
                                                                    //System.out.println("Replace " + getSelectedItem().toString + " with " + v)
                                                                    //if (getSelectedItem().toString != v) setSelectedItem(v)
                                                                    howToRepaint()
                                                                }
                                                            def howToSetPopup() = 
                                                                {
                                                                    if (popup().size > 0)  
                                                                        {
                                                                            val p = new javax.swing.JPopupMenu
                                                                            popup().foreach({q => p.add(q.component())})
                                                                            setComponentPopupMenu(p)
                                                                        } else setComponentPopupMenu(null) 
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetItems(_x : List[String]) =
                                                                {
                                                                    val newIndex = TryCatch(_x.indexOf(content()), -1)
                                                                    setModel(new javax.swing.DefaultComboBoxModel(_x.toArray))
                                                                    //System.out.println("New Index = " + newIndex)
                                                                    setSelectedIndex(newIndex)
                                                                    howToRepaint()
                                                                }
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                                
    
                                                            addActionListener(
                                                                new java.awt.event.ActionListener
                                                                    {
                                                                        def actionPerformed(_e : java.awt.event.ActionEvent) = 
                                                                            Try(
                                                                                {
                                                                                    val newValue = items()(getSelectedIndex)
                                                                                    //System.out.println("New Value ='" + newValue + "'")
                                                                                    content.assign(newValue)
                                                                                })
                                                                    })
                                                            setOpaque(true)
                                                            setBackground(java.awt.Color.WHITE)
                                                        }
                                        def component() = __component
                                        border(new javax.swing.JComboBox[String]().getBorder)
                                        maximalHeight(new javax.swing.JTextField(" ").getPreferredSize().getHeight.toInt)
                                        def selectFirstItem() : this.type = 
                                            {
                                                items()
                                                    .take(1)
                                                    .foreach(
                                                        {i => 
                                                            content.assign(i)
                                                        })
                                                this
                                            }
                                    }
                            class ListBox()
                                extends Framework.ListBox.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iContent[List[String]]
                                with Rock.iHasList
                                with Rock.iHasPopup
                                with Rock.iPreferredSize
                                    {__owner => 
                                        protected lazy val __listBox =
                                                new javax.swing.JList[String]()
                                                    {
                                                        addListSelectionListener(
                                                            new javax.swing.event.ListSelectionListener
                                                                {
                                                                    def valueChanged(_e : javax.swing.event.ListSelectionEvent) = 
                                                                        Try(
                                                                            {
                                                                                val newValue = 
                                                                                    getSelectedIndices()
                                                                                        .map({i => items()(i)})
                                                                                        .toList
                                                                                //System.out.println("New Value ='" + newValue + "'")
                                                                                content.assign(newValue)
                                                                            })
                                                                })
                                                        setOpaque(true)
                                                        setBackground(java.awt.Color.WHITE)
                                                        setInheritsPopupMenu(true)
                                                    }
                                        
                                        protected lazy val __component =
                                                new javax.swing.JScrollPane(__listBox)
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iContent[List[String]]
                                                    with Java.iHasList
                                                    with Java.iHasPopup
                                                    with Java.iPreferredSize
                                                        {
                                                            def owner() = __owner
                                                            def howToSetContent(_x : List[String]) =
                                                                {
                                                                    val newIndexes = _x.map({s => items().indexOf(s)})
                                                                    __listBox.setSelectedIndices(newIndexes.toArray)
                                                                    howToRepaint()
                                                                }
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetItems(_x : List[String]) =
                                                                {
                                                                    //System.out.println("\nChange Items:")
                                                                    val newIndexes = 
                                                                        content()
                                                                            .map(
                                                                                {s =>
                                                                                    TryCatch(
                                                                                        {
                                                                                            _x.indexOf(s)
                                                                                        }, -1)
                                                                                })
                                                                            .filter(_ != -1)
                                                                    //System.out.println("    current selection: " + content().mkString(", "))
                                                                    //System.out.println("    new     selection: " + newIndexes.mkString(", "))
                                                                    __listBox.setModel(new javax.swing.DefaultComboBoxModel(_x.toArray))
                                                                    //System.out.println("    Model is set.")
                                                                    __listBox.setSelectedIndices(newIndexes.toArray)
                                                                    //System.out.println("    New selection is made.")
                                                                    howToRepaint()
                                                                }
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetPopup() = 
                                                            {
                                                                if (popup().size > 0)  
                                                                    {
                                                                        val p = new javax.swing.JPopupMenu
                                                                        popup().foreach({q => p.add(q.component())})
                                                                        setComponentPopupMenu(p)
                                                                    } else setComponentPopupMenu(null) 
                                                            }
                                                                
                                                            
                                                            setOpaque(true)
                                                            setBackground(java.awt.Color.WHITE)
                                                            border(new javax.swing.JTextField().getBorder)
                                                        }
                                        def component() = __component
                                        border(new javax.swing.JComboBox[String]().getBorder)
                                    }
                            class ScrollBar()
                                extends Framework.ScrollBar.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iFocusable
                                with Rock.iSliderData
                                with Rock.iDirection
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JScrollBar()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iFocusable
                                                    with Java.iSliderData
                                                    with Java.iDirection
                                                        {
                                                            def owner() = __owner
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetMinimum(_x : Int) = setMinimum(_x)
                                                            def howToSetValue(_x : Int) = setValue(_x)
                                                            def howToSetMaximum(_x : Int) = setMaximum(_x)
                                                            def howToRepaint() = 
                                                                {
                                                                    setOrientation(if (horizontalDirection()) java.awt.Adjustable.HORIZONTAL else java.awt.Adjustable.VERTICAL)
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            
                                                            addAdjustmentListener(
                                                                new java.awt.event.AdjustmentListener
                                                                    {
                                                                        override def adjustmentValueChanged(_e : java.awt.event.AdjustmentEvent) = value.assign(getValue)
                                                                    })
    
                                                        }
                                        def component() = __component
                                    }
                        // Menus
                            class MenuBar()
                                extends Framework.MenuBar.Interface
                                with Rock.iComponent 
                                with Rock.iSimplePanel
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JMenuBar()
                                                    with Java.iComponent
                                                    with Java.iSimplePanel
                                                        {
                                                            def owner() = __owner
                                                            def howToClearComponents() = removeAll()
                                                            def howToAddComponent(_x : java.awt.Component) = add(_x)
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                        }
                                        def component() = __component
                                        def actionList(_k : KeySequence.Interface, _filter : Framework.iKeyActionData => Boolean) : List[() => Any] = 
                                            componentList()
                                                .cast({case p : Framework.Menu.Interface => p})
                                                .map({_.actionList(_k, _filter)})
                                                .foldLeft(List[() => Any]())(_ ++ _)
                                        
                                    }
                            class Menu()
                                extends Framework.Menu.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iHasMnemonic
                                with Rock.iSimplePanel
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JMenu()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iHasMnemonic
                                                    with Java.iSimplePanel
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = setText(_x)
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToClearComponents() = removeAll()
                                                            def howToAddComponent(_x : java.awt.Component) = add(_x)
                                                            def howToSetMnemonic(_x : String) = setMnemonic(if (_x == null || _x.size == 0) 0 else _x.getBytes()(0))
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                        }
                                        def component() = __component
                                        override def actionList(_k : KeySequence.Interface, _filter : Framework.iKeyActionData => Boolean) : List[() => Any] =
                                            {
                                                //System.out.println("Checking " + _k + " for " + caption())
                                                componentList()
                                                    .map(
                                                        {c =>
                                                            c match
                                                                {
                                                                    case p : Framework.Menu.Interface =>
                                                                        p.actionList(_k, _filter)
                                                                    case p : Framework.iHasKeyActions =>
                                                                        p.actionList(_k, _filter)
                                                                    case _ =>
                                                                        List[() => Any]()
                                                                }
                                                        })
                                                    .foldLeft(List[() => Any]())(_ ++ _)
                                            }
                                    }
                                    
                            class Separator()
                                extends Framework.Separator.Interface
                                with Rock.iComponent 
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JSeparator()
                                                    with Java.iComponent
                                                        {
                                                            def owner() = __owner
                                                        }
                                        def component() = __component
                                    }
                            class MenuItem()
                                extends Framework.MenuItem.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iFocusable
                                with Rock.iPreferredSize
                                with Rock.iBorder
                                with Rock.iHasMnemonic
                                with Rock.iHasAccelerator
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JMenuItem
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iPreferredSize
                                                    with Java.iFocusable
                                                    with Java.iBorder
                                                    with Java.iHasMnemonic
                                                    with Java.iHasAccelerator
                                                        {
    
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = 
                                                                {
                                                                    //System.out.println("A")
                                                                    setText(owner().caption())
                                                                    //System.out.println("B")
                                                                    lbl2.setText(TryCatch(accelerator().toString, ""))
                                                                    //System.out.println("Repainting for: " + owner().caption() + "  " + TryCatch(accelerator().toString, ""))
                                                                    howToRepaint()
                                                                    //System.out.println("C")
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetFocusListener(_x : java.awt.event.FocusListener) = addFocusListener(_x)
                                                            def howToSetMnemonic(_x : String) = setMnemonic(if (_x == null || _x.size == 0) 0 else _x.getBytes()(0))
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
    
                                                            howToSetCaption("")
                                                            lazy val spaceSize : Int = new javax.swing.JLabel(" ").getPreferredSize.getWidth.toInt
                                                            private lazy val lbl2 : javax.swing.JLabel = new javax.swing.JLabel(" ")
                                                            add(lbl2)                                                        
                                                            override def getText : String = TryCatch(super.getText + new Range(0, 4 + lbl2.getPreferredSize.getWidth.toInt/spaceSize, 1).map({_ =>  " "}).mkString, super.getText)
                                                            setLayout(
                                                                new java.awt.LayoutManager
                                                                    {
                                                                        def addLayoutComponent(_name : String, _comp : java.awt.Component) = {}
                                                                        def removeLayoutComponent(_comp : java.awt.Component) = {}
                                                                        def layoutContainer(_parent : java.awt.Container) = 
                                                                            {
                                                                                lbl2.setBounds(_parent.getWidth - 10 - lbl2.getPreferredSize.getWidth.toInt,0,lbl2.getPreferredSize.getWidth.toInt, _parent.getHeight)
                                                                            }
                                                                        def minimumLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = preferredLayoutSize(_parent)
                                                                        def preferredLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = new java.awt.Dimension(0,0)
                                                                    })
                                                        }
                                        def component() = __component
                                        override def actionList(_k : KeySequence.Interface, _filter : Framework.iKeyActionData => Boolean) : List[() => Any] =
                                            {
                                                object AcceleratorKeyActionData
                                                    extends Framework.iKeyActionData
                                                        {
                                                            def exactMatch(_k : KeySequence.Interface) : Boolean = _k == accelerator()
                                                            def smallerMatch(_k : KeySequence.Interface) : Boolean = _k <= accelerator()
                                                            def action() : Any = click()
                                                        }
                                                if (_filter(AcceleratorKeyActionData)) 
                                                    {
                                                        List[() => Any]({() => click()}) ++ super.actionList(_k, _filter)
                                                    } else super.actionList(_k, _filter)
                                            }
                                    }
                            
                        // Panels
                            class BorderPanel()
                                extends Framework.BorderPanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iPreferredSize
                                with Rock.iBorder
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JPanel(new java.awt.BorderLayout)
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iPreferredSize
                                                    with Java.iBorder
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                        }
                                        def component() = __component
                                        private def add(_s : String, _x : Framework.iAddableComponent) : this.type = Reflect({__component.add(_s, _x.component())}, this)
                                        def north(_x : Framework.iAddableComponent) : this.type = add(java.awt.BorderLayout.NORTH, _x)
                                        def west(_x : Framework.iAddableComponent) : this.type = add(java.awt.BorderLayout.WEST, _x)
                                        def center(_x : Framework.iAddableComponent) : this.type = add(java.awt.BorderLayout.CENTER, _x)
                                        def east(_x : Framework.iAddableComponent) : this.type = add(java.awt.BorderLayout.EAST, _x)
                                        def south(_x : Framework.iAddableComponent) : this.type = add(java.awt.BorderLayout.SOUTH, _x)
                                        
                                    }
                            class ScrollPanel()
                                extends Framework.ScrollPanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iPreferredSize
                                with Rock.iBorder
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JScrollPane()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iPreferredSize
                                                    with Java.iBorder
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                        }
                                        def component() = __component
                                        def add(_x : Framework.iAddableComponent) : this.type = Reflect({__component. 	getViewport.add(_x.component())}, this)
                                    }
                            class DistributedPanel()
                                extends Framework.DistributedPanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iHasMargin
                                with Rock.iSimplePanel
                                with Rock.iDirection
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JPanel()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iHasMargin
                                                    with Java.iSimplePanel
                                                    with Java.iDirection
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetMargin(_x : Int) = {howToRepaint}
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToClearComponents() = removeAll()
                                                            def howToAddComponent(_x : java.awt.Component) = add(_x)
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                            setLayout(
                                                                {
                                                                    new java.awt.LayoutManager
                                                                        {
                                                                            def addLayoutComponent(_name : String, _comp : java.awt.Component) = {}
                                                                            def removeLayoutComponent(_comp : java.awt.Component) = {}
                                                                            def layoutContainer(_parent : java.awt.Container) = 
                                                                                {
                                                                                    if (horizontalDirection()) 
                                                                                        {
                                                                                            val w = getComponents().map(_.getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_,_))
                                                                                            val space = (getWidth.toInt - getComponentCount() * w)/(1+ getComponentCount())
                                                                                            var x = space
                                                                                            getComponents
                                                                                                .foreach(
                                                                                                    {c =>
                                                                                                        c.setBounds(x,margin(), w, c.getPreferredSize.getHeight.toInt)
                                                                                                        x += space + w
                                                                                                    })
                                                                                        } 
                                                                                    else 
                                                                                        {
                                                                                            val h = getComponents().map(_.getPreferredSize.getHeight.toInt).foldLeft(0)(Math.max(_,_))
                                                                                            val space = (getHeight.toInt - getComponentCount() * h)/(1+ getComponentCount())
                                                                                            var y = space
                                                                                            getComponents
                                                                                                .foreach(
                                                                                                    {c =>
                                                                                                        c.setBounds(margin(), y, c.getPreferredSize.getWidth.toInt, h)
                                                                                                        y += space + h
                                                                                                    })
                                                                                        }
                                                                                }
                                                                            def minimumLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = preferredLayoutSize(_parent)
                                                                            def preferredLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = 
                                                                                {
                                                                                    val w = getComponents().map(_.getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_,_))
                                                                                    val h = getComponents().map(_.getPreferredSize.getHeight.toInt).foldLeft(0)(Math.max(_,_))
                                                                                    if (horizontalDirection()) 
                                                                                        {
                                                                                            new java.awt.Dimension(getComponentCount * (w + margin()), 2 * margin() + h) 
                                                                                        } 
                                                                                    else 
                                                                                        {
                                                                                            new java.awt.Dimension(2 * margin() + w, getComponentCount * (h + margin()))
                                                                                        }  
                                                                                }
                                                                        }
                                                                })
                                                        }
                                        def component() = __component
                                    }
                            class TwoColumnPanel()
                                extends Framework.TwoColumnPanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iPreferredSize
                                with Rock.iClickable
                                with Rock.iSimplePanel
                                with Rock.iBorder
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JPanel()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iPreferredSize
                                                    with Java.iClickable
                                                    with Java.iSimplePanel
                                                    with Java.iBorder
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToClearComponents() = removeAll()
                                                            def howToAddComponent(_x : java.awt.Component) = add(_x)
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()
                                                                    paint(getGraphics)
                                                                }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                            setLayout(
                                                                {
                                                                    val margin = 5
                                                                    new java.awt.LayoutManager
                                                                        {
                                                                            def addLayoutComponent(_name : String, _comp : java.awt.Component) = {}
                                                                            def removeLayoutComponent(_comp : java.awt.Component) = {}
                                                                            def layoutContainer(_parent : java.awt.Container) = 
                                                                                {
                                                                                    val w1 = Range(0,getComponentCount,2).map(getComponent(_).getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_, _))
                                                                                    var y = margin
                                                                                    var yx = 0
                                                                                    Range(0,getComponentCount,2)
                                                                                        .foreach(
                                                                                            {i =>
                                                                                                // Label
                                                                                                    Range(i,i+1)
                                                                                                        .foreach(
                                                                                                            {i => 
                                                                                                                val c = getComponent(i)
                                                                                                                yx = c.getPreferredSize.getHeight.toInt
                                                                                                                c.setBounds(margin, y, w1, yx)
                                                                                                            })
                                                                                                // Component
                                                                                                    Range(i+1,i+2)
                                                                                                        .foreach(
                                                                                                            {i => 
                                                                                                                Try(
                                                                                                                    {
                                                                                                                        val c = getComponent(i)
                                                                                                                        val nx = c.getPreferredSize.getHeight.toInt
                                                                                                                        c.setBounds(margin + w1 + margin, y, getWidth.toInt - margin - margin - w1 - margin, nx)
                                                                                                                        yx = Math.max(yx, nx)
                                                                                                                    })
                                                                                                            })
                                                                                                y += margin + yx
                                                                                            })
                                                                                }
                                                                            def minimumLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = preferredLayoutSize(_parent)
                                                                            def preferredLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = 
                                                                                {
                                                                                    val w1 = Range(0,getComponentCount,2).map(getComponent(_).getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_, _))
                                                                                    val w2 = Range(1,getComponentCount,2).map(getComponent(_).getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_, _))
                                                                                    val h = 
                                                                                        Range(0,getComponentCount,2)
                                                                                            .map(
                                                                                                {i =>
                                                                                                    TryCatch(Range(i,i+2,1).map(getComponent(_).getPreferredSize.getHeight.toInt).foldLeft(0)(Math.max(_, _)),0) + margin
                                                                                                })
                                                                                            .foldLeft(0)(_ + _)
                                                                                    new java.awt.Dimension(margin + w1 + margin + w2 + margin, margin + h + margin)
                                                                                }
                                                                                
                                                                        }
                                                                })
                                                        }
                                        def component() = __component
                                        def add(_s : String, _x : Framework.iAddableComponent) : this.type = add(Framework.Label(_s)).add(_x) 
                                        
                                    }
                            class GridPanel()
                                extends Framework.GridPanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iHasMargin
                                with Rock.iSimplePanel
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JPanel()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iHasMargin
                                                    with Java.iSimplePanel
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetMargin(_x : Int) = 
                                                                {
                                                                    howToSetLayout()
                                                                    howToRepaint()
                                                                }
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetLayout() =
                                                                {
                                                                    val n = owner.componentList().size
                                                                    val sn = Math.sqrt(0.0 + n).toInt
                                                                    val w = if (sn*sn == n) sn else sn + 1
                                                                    if (w != 0) setLayout(new java.awt.GridLayout(w, w, margin(), margin()))
                                                                    
                                                                }
                                                            def howToClearComponents() = 
                                                                {
                                                                    removeAll()
                                                                    howToSetLayout()
                                                                }
                                                                
                                                            def howToAddComponent(_x : java.awt.Component) = 
                                                                {
                                                                    add(_x)
                                                                }
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                        }
                                        def component() = __component
                                    }
                            class SplitPanel()
                                extends Framework.SplitPanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iPreferredSize
                                with Rock.iBorder
                                with Rock.iSplitPanel
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JSplitPane()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iPreferredSize
                                                    with Java.iBorder
                                                    with Java.iSplitPanel
                                                        {
                                                            def owner() = __owner
                                                            def howToRepaint() = 
                                                                {
                                                                    setOrientation(if (horizontalDirection()) javax.swing.JSplitPane.HORIZONTAL_SPLIT else javax.swing.JSplitPane.VERTICAL_SPLIT)
                                                                    setDividerLocation(sliderPosition())
                                                                    if (left() == null) Try(remove(getLeftComponent)) else setLeftComponent(left().component())
                                                                    if (right() == null) Try(remove(getRightComponent)) else setRightComponent(right().component())
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                            addPropertyChangeListener(
                                                                javax.swing.JSplitPane.DIVIDER_LOCATION_PROPERTY, 
                                                                new java.beans.PropertyChangeListener() 
                                                                    {
                                                                        override def propertyChange(_e : java.beans.PropertyChangeEvent) = 
                                                                            {
                                                                                //System.out.println("Old position:     " + sliderPosition())
                                                                                sliderPosition.assign(getDividerLocation)
                                                                                //System.out.println("    new position: " + sliderPosition())
                                                                            }
                                                                    })
                                                        }
                                        def component() = __component
                                    }
                            class QueuePanel()
                                extends Framework.QueuePanel.Interface
                                with Rock.iComponent 
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iBackColor
                                with Rock.iEnabled
                                with Rock.iClickable
                                with Rock.iBorder
                                with Rock.iHasMargin
                                with Rock.iSimplePanel
                                with Rock.iDirection
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JPanel()
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iBackColor
                                                    with Java.iEnabled
                                                    with Java.iClickable
                                                    with Java.iBorder
                                                    with Java.iHasMargin
                                                    with Java.iSimplePanel
                                                    with Java.iDirection
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = {}
                                                            def howToSetMargin(_x : Int) = {howToRepaint}
                                                            def howToSetEnabled(_x : Boolean) = setEnabled(_x)
                                                            def howToSetBorder(_x : javax.swing.border.Border) = setBorder(_x)
                                                            def howToClearComponents() = removeAll()
                                                            def howToAddComponent(_x : java.awt.Component) = add(_x)
                                                            def howToRepaint() = 
                                                                {
                                                                    revalidate()      
                                                                    invalidate()
                                                                    repaint()
                                                                }
                                                            def howToSetBackColor(_x : Option[java.awt.Color]) = 
                                                                {
                                                                    _x match 
                                                                        {
                                                                            case None => 
                                                                                setOpaque(false)
                                                                            case Some(x) =>
                                                                                setOpaque(true)
                                                                                setBackground(x)
                                                                        }
                                                                }
                                                            setLayout(
                                                                {
                                                                    new java.awt.LayoutManager
                                                                        {
                                                                            def addLayoutComponent(_name : String, _comp : java.awt.Component) = {}
                                                                            def removeLayoutComponent(_comp : java.awt.Component) = {}
                                                                            def layoutContainer(_parent : java.awt.Container) = 
                                                                                {
                                                                                    if (horizontalDirection()) 
                                                                                        {
                                                                                            val w = getComponents().map(_.getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_,_))
                                                                                            val space = (getWidth.toInt - getComponentCount() * w)/(1+ getComponentCount())
                                                                                            var x = space
                                                                                            getComponents
                                                                                                .foreach(
                                                                                                    {c =>
                                                                                                        c.setBounds(x,margin(), w, c.getPreferredSize.getHeight.toInt)
                                                                                                        x += space + w
                                                                                                    })
                                                                                        } 
                                                                                    else 
                                                                                        {
                                                                                            val h = getComponents().map(_.getPreferredSize.getHeight.toInt).foldLeft(0)(Math.max(_,_))
                                                                                            val space = (getHeight.toInt - getComponentCount() * h)/(1+ getComponentCount())
                                                                                            var y = space
                                                                                            getComponents
                                                                                                .foreach(
                                                                                                    {c =>
                                                                                                        c.setBounds(margin(), y, c.getPreferredSize.getWidth.toInt, h)
                                                                                                        y += space + h
                                                                                                    })
                                                                                        }
                                                                                }
                                                                            def minimumLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = preferredLayoutSize(_parent)
                                                                            def preferredLayoutSize(_parent : java.awt.Container) : java.awt.Dimension = 
                                                                                {
                                                                                    val w = getComponents().map(_.getPreferredSize.getWidth.toInt).foldLeft(0)(Math.max(_,_))
                                                                                    val h = getComponents().map(_.getPreferredSize.getHeight.toInt).foldLeft(0)(Math.max(_,_))
                                                                                    if (horizontalDirection()) 
                                                                                        {
                                                                                            new java.awt.Dimension(getComponentCount * (w + margin()), 2 * margin() + h) 
                                                                                        } 
                                                                                    else 
                                                                                        {
                                                                                            new java.awt.Dimension(2 * margin() + w, getComponentCount * (h + margin()))
                                                                                        }  
                                                                                }
                                                                        }
                                                                })
                                                        }
                                        def component() = __component
                                    }
                        //Frames
                            class Dialog()
                                extends Framework.Dialog.Interface
                                with Rock.iComponent
                                with Rock.iWindow
                                with Rock.iHasKeyActions
                                with Rock.iCaption
                                with Rock.iHasMenuBar
                                    {__owner => 
                                        protected lazy val __component =
                                                new javax.swing.JDialog
                                                    with Java.iComponent
                                                    with Java.iHasKeyActions
                                                    with Java.iCaption
                                                    with Java.iWindow
                                                    with Java.iHasMenuBar
                                                        {
                                                            def owner() = __owner
                                                            def howToSetCaption(_x : String) = setTitle(_x)
                                                            def howToAddComponent(_x : java.awt.Component) = 
                                                                {
                                                                    getContentPane.removeAll
                                                                    if (_x != null) add(_x)
                                                                }
                                                            def howToSetMenuBar(_x : Framework.MenuBar.Interface) = 
                                                                {
                                                                    _x.component match
                                                                        {
                                                                            case p : javax.swing.JMenuBar =>
                                                                                setJMenuBar(p)
                                                                            case _ =>
                                                                        }
                                                                }
        
                                                            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE)
                                                            setModal(true)
                                                        }
                                        def component() = __component
                                        def close() : this.type = Reflect(__component.dispose(), this)
                                        def show() = 
                                            {
                                                WindowsRefresher.refresh(__component) 
                                                __component.pack
                                                __component.setLocationRelativeTo(null)
                                                __component.setVisible(true)
                                                this
                                            }
                                        override def actionList(_k : KeySequence.Interface, _filter : Framework.iKeyActionData => Boolean) : List[() => Any] =
                                            {
                                                menu() match
                                                    {
                                                        case null =>
                                                            super.actionList(_k, _filter)
                                                        case x =>
                                                            super.actionList(_k, _filter) ++ x.actionList(_k, _filter)  
                                                    }
                                            }
                                    }
                            
                    def initialise() =     
                        {
                            val Framework =  nl.datakneder.core.UI.Rock.Framework
                            val Implementation =  nl.datakneder.core.UI.Rock.Implementation
                            
                            Framework.WindowsRefresher.assign(Implementation.WindowsRefresher)
                            java.awt.KeyboardFocusManager.setCurrentKeyboardFocusManager(KeyboardFocusManager)
                          
                            // Editor
                                Framework.Editor.assign(Implementation.Editor)
                                Framework.Editor.add({_ => _ => 
                                    {case (p : Framework.Editor.iConstructionData, n : String) => 
                                        Framework.TextField()
                                            .content.applyCast(p.read)
                                            .content.update(p.write)
                                    }})
                                Framework.Editor.add({_ => _ => 
                                    {case (p : Framework.Editor.iConstructionData, n : Boolean) => 
                                        Framework.CheckBox()
                                            .content.applyCast(p.read)
                                            .content.update(p.write)
                                    }})
                                Framework.Editor.add({_ => _ => 
                                    {case (p : Framework.Editor.iConstructionData, n : Int) => 
                                        Framework.TextField()
                                            .content.applyCast({() => p.read().toString})
                                            .content.update({x => Try(p.write(x.toInt))})
                                    }})
                                Framework.Editor.add({_ => _ => 
                                    {case (p : Framework.Editor.iConstructionData, n : List[_]) => 
                                        Framework.ListBox()
                                            .items(
                                                {() => 
                                                    p.read() match
                                                        {
                                                            case p : List[_] =>
                                                                p.map(_.toString)
                                                            case _ =>
                                                                List[String]()
                                                        }
                                                })
                                    }})
                            // Plain
                                Framework.Button.constructor({() => new Implementation.Button()})
                                Framework.Label.constructor({() => new Implementation.Label()})
                                Framework.CheckBox.constructor({() => new Implementation.CheckBox()})
                                Framework.TextField.constructor({() => new Implementation.TextField()})
                                Framework.IntField.constructor({() => new Implementation.IntField()})
                                Framework.PasswordField.constructor({() => new Implementation.PasswordField()})
                                Framework.KeyButton.constructor({() => new Implementation.KeyButton()})
                                Framework.Slider.constructor({() => new Implementation.Slider()})
                                Framework.Spinner.constructor({() => new Implementation.Spinner()})
                                Framework.ColorPicker.constructor({() => new Implementation.ColorPicker()})
                                Framework.ComboBox.constructor({() => new Implementation.ComboBox()})
                                Framework.ListBox.constructor({() => new Implementation.ListBox()})
                                Framework.ScrollBar.constructor({() => new Implementation.ScrollBar()})
                            // Menus
                                Framework.MenuBar.constructor({() => new Implementation.MenuBar()})
                                Framework.Menu.constructor({() => new Implementation.Menu()})
                                Framework.Separator.constructor({() => new Implementation.Separator()})
                                Framework.MenuItem.constructor({() => new Implementation.MenuItem()})
                            // Panels
                                Framework.BorderPanel.constructor({() => new Implementation.BorderPanel()})
                                Framework.ScrollPanel.constructor({() => new Implementation.ScrollPanel()})
                                Framework.TwoColumnPanel.constructor({() => new Implementation.TwoColumnPanel()})
                                Framework.DistributedPanel.constructor({() => new Implementation.DistributedPanel()})
                                Framework.GridPanel.constructor({() => new Implementation.GridPanel()})
                                Framework.SplitPanel.constructor({() => new Implementation.SplitPanel()})
                                Framework.QueuePanel.constructor({() => new Implementation.QueuePanel()})
                            // Frames
                                Framework.Dialog.constructor({() => new Implementation.Dialog()})
                                
                            WindowsRefresher() 
                        }
                }
    }
package nl.datakneder.core.UI.Rock
    {
        import nl.datakneder.core.Packages._
        import nl.datakneder.core.Acid.Framework.Acid
        
        object Test
            extends Template_TestPackage("Rock", 1492890084584L /*CompileDate*/, nl.datakneder.core.UI.Rock.Framework)
                {
                    class Rock()
                        extends nl.datakneder.core.Acid.Implementation.TestSet.Class("Rock")
                            {
                                def run() : Unit = 
                                    {
                                        // Initialise
                                            nl.datakneder.core.Utils.Implementation.initialise()
                                            import nl.datakneder.core.Utils.Framework._
                                            import nl.datakneder.core.UI.Rock.Framework._
                                            import nl.datakneder.core.UI.Rock.Implementation.WindowsRefresher 
                                            import nl.datakneder.core.Data.Framework.KeySequence 
                                            import nl.datakneder.core.Data.Framework.KeyManager
                                            import nl.datakneder.core.Data.Framework.KeyDefinitions._ 
                                            nl.datakneder.core.UI.Rock.Implementation.initialise()
                                            nl.datakneder.core.Data.Implementation.initialise()
                                            
                                        //WindowsRefresher.refreshRate(500)()
                                        WindowsRefresher()
                                        //nl.datakneder.core.framework.data.Keys.Debug(true)
                                        val Framework = nl.datakneder.core.UI.Rock.Framework
                                        
                                        object TestDialog
                                            {
                                                def apply(_title : String, _question : String, _component : Framework.iAddableComponent, _f : Dialog.Interface => Unit) : Boolean = 
                                                    {
                                                        var result = false
                                                        val dialog = Dialog()
                                                        def cancel() = dialog.close()
                                                        def ok() = 
                                                            {
                                                                result = true
                                                                dialog.close()
                                                            }
                                                        dialog
                                                            .caption(_title)
                                                            .keyAction(VK_Escape, cancel _)
                                                            .keyAction(VK_Enter, ok _)
                                                            .add(
                                                                BorderPanel()
                                                                    .north(Label(_question).margin(20))
                                                                    .center(
                                                                        BorderPanel()
                                                                            .center(_component)
                                                                            .margin(20))
                                                                    .south(
                                                                        BorderPanel()
                                                                            .margin(10)
                                                                            .center(
                                                                                DistributedPanel()
                                                                                    .horizontal()
                                                                                    .add(Button("Cancel").onClick(cancel _))
                                                                                    .add(Button("Ok").onClick(ok _))
                                                                                    )))
                                                        _f(dialog)
                                                        dialog.show()
                                                        result
                                                    }
                                            }
                                        
                                        case class TestSet(
                                            name : String,
                                            action : () => Boolean)
                                        
                                        val testSets = 
                                            List[TestSet](
                                                    TestSet("BorderPanel", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test BorderPanel", "Resize window.",
                                                                            BorderPanel()
                                                                                .north(Button("North"))
                                                                                .west(Button("West"))
                                                                                .center(Button("Center"))
                                                                                .east(Button("East"))
                                                                                .south(Button("South")),
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("Borders", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    val lbl = 
                                                                        Label("Label")
                                                                    Acid(
                                                                        TestDialog("Test BorderPanel", "Resize window.",
                                                                            BorderPanel()
                                                                                .center(
                                                                                    BorderPanel()
                                                                                        .lineBorder(1)
                                                                                        .margin(20)
                                                                                        .center(lbl))
                                                                                .south(
                                                                                    DistributedPanel()
                                                                                        .horizontal()
                                                                                        .add(Button("Clear").onClick({() => lbl.clearBorder()}))
                                                                                        .add(Button("Line")
                                                                                            .onClick(
                                                                                                {() => 
                                                                                                    var thickness = 10
                                                                                                    var color = java.awt.Color.WHITE
                                                                                                    val dialog = Dialog()
                                                                                                    def ok() = 
                                                                                                        {
                                                                                                            lbl.lineBorder(color, thickness)
                                                                                                            dialog.close()
                                                                                                        }
                                                                                                    def cancel() = dialog.close()
                                                                                                    dialog
                                                                                                        .add(
                                                                                                            BorderPanel()
                                                                                                                .center(
                                                                                                                    TwoColumnPanel()
                                                                                                                        .add("Thickness", Spinner().value({() => thickness}).value.update({(x : Int) => thickness = x}))
                                                                                                                        .add("Color", ColorPicker().content({() => color}).content.update({(x : java.awt.Color) => color = x})))
                                                                                                                .south(
                                                                                                                    DistributedPanel()
                                                                                                                        .horizontal()
                                                                                                                        .add(Button("Cancel").onClick(cancel _))
                                                                                                                        .add(Button("Ok").onClick(ok _))
                                                                                                                        )
                                                                                                            )
                                                                                                        .show()
                                                                                                }))
                                                                                        .add(Button("Margin")
                                                                                            .onClick(
                                                                                                {() => 
                                                                                                    var thickness = 10
                                                                                                    val dialog = Dialog()
                                                                                                    def ok() = 
                                                                                                        {
                                                                                                            lbl.margin(thickness)
                                                                                                            dialog.close()
                                                                                                        }
                                                                                                    def cancel() = dialog.close()
                                                                                                    dialog
                                                                                                        .add(
                                                                                                            BorderPanel()
                                                                                                                .center(
                                                                                                                    TwoColumnPanel()
                                                                                                                        .add("Size", Spinner().value({() => thickness}).value.update({(x : Int) => thickness = x})))
                                                                                                                .south(
                                                                                                                    DistributedPanel()
                                                                                                                        .horizontal()
                                                                                                                        .add(Button("Cancel").onClick(cancel _))
                                                                                                                        .add(Button("Ok").onClick(ok _))
                                                                                                                        )
                                                                                                            )
                                                                                                        .show()
                                                                                                }))
                                                                                        .add(Button("Titled")
                                                                                            .onClick(
                                                                                                {() => 
                                                                                                    var title = ""
                                                                                                    val dialog = Dialog()
                                                                                                    def ok() = 
                                                                                                        {
                                                                                                            lbl.titleBorder(title)
                                                                                                            dialog.close()
                                                                                                        }
                                                                                                    def cancel() = dialog.close()
                                                                                                    dialog
                                                                                                        .add(
                                                                                                            BorderPanel()
                                                                                                                .center(
                                                                                                                    TwoColumnPanel()
                                                                                                                        .add("Title", TextField().content({() => title}).content.update({(x : String) => title = x})))
                                                                                                                .south(
                                                                                                                    DistributedPanel()
                                                                                                                        .horizontal()
                                                                                                                        .add(Button("Cancel").onClick(cancel _))
                                                                                                                        .add(Button("Ok").onClick(ok _))
                                                                                                                        )
                                                                                                            )
                                                                                                        .show()
                                                                                                }))
                                                                                        ),
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("DistributedPanel", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test DistributedPanel", "Add buttons and clear them.",
                                                                            {
                                                                                var horizontal = true
                                                                                val dp = 
                                                                                    DistributedPanel()
                                                                                        .horizontalDirection({() => horizontal})
    
                                                                                BorderPanel()
                                                                                    .height(_ => 200)
                                                                                    .width(_ => 500)
                                                                                    .center(dp)
                                                                                    .south(
                                                                                        DistributedPanel().horizontal()
                                                                                            .add(Button("Add").onClick({() => dp.add(Button("New"))}))
                                                                                            .add(Button("Swap Direction").onClick({() => horizontal = !horizontal}))
                                                                                            .add(Button("Clear").onClick({() => dp.clear()})))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("CheckBox", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test CheckBox", "Select the checkbox.",
                                                                            {
                                                                                var enabled = true
                                                                                TwoColumnPanel()
                                                                                    .add("enabled",CheckBox().content({() => enabled}).content.update(enabled = _))
                                                                                    .add("enabled",CheckBox().content({() => enabled}).content.update(enabled = _))
                                                                                    .add("Button", Button("Ok").enabled({() => enabled}))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("TextField", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test TextField", "Write something in te textfield.",
                                                                            {
                                                                                var name = "Herman"
                                                                                var enabled = false
                                                                                TwoColumnPanel()
                                                                                    .add("Name",TextField().content({() => name}).content.update(name = _))
                                                                                    .add("Name",
                                                                                        TextField()
                                                                                            .content({() => name})
                                                                                            .content.update(name = _)
                                                                                            .backColor({() => if (enabled) Some(java.awt.Color.WHITE) else Some(java.awt.Color.RED)})
                                                                                            .enabled({() => enabled}))
                                                                                    .add("Enabled", CheckBox().content({() => enabled}).content.update(enabled = _))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("KeyButton", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test KeyButton", "Write something in te textfield.",
                                                                            {
                                                                                var key : KeySequence.Interface = NoModifier
                                                                                TwoColumnPanel()
                                                                                    .add("Key",KeyButton().content({() => key}).content.update(key = _))
                                                                                    .add("Key",KeyButton().content({() => key}).content.update(key = _))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("PasswordField", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test PasswordField", "Write something in te textfield.",
                                                                            {
                                                                                var name = "Herman"
                                                                                TwoColumnPanel()
                                                                                    .add("Name",PasswordField().content({() => name}).content.update(name = _))
                                                                                    .add("Name",PasswordField().content({() => name}).content.update(name = _))
                                                                                    .add("Name",TextField().content({() => name}).content.update(name = _))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("Menus", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test Menus", "Look at the menus and perform the actions.",
                                                                            {
                                                                                Label("Nothing.")
                                                                            },
                                                                            {dialog =>
                                                                                dialog
                                                                                    .menu(
                                                                                        MenuBar(
                                                                                            {menuBar => 
                                                                                                    menuBar.add(
                                                                                                        Menu("Menu 1")
                                                                                                            .mnemonic("M")
                                                                                                            .add(Menu("Submenu 1"))
                                                                                                            .add(Menu("Submenu 2"))
                                                                                                            .add(Separator())
                                                                                                            .add(Menu("After Separator"))
                                                                                                            .add(
                                                                                                                MenuItem(
                                                                                                                    {item => 
                                                                                                                        item
                                                                                                                            .caption("Click will change text.")
                                                                                                                            .mnemonic("C")
                                                                                                                            .accelerator(CTRL + "A")
                                                                                                                            .onClick({() => item.caption("Ok.")})
                                                                                                                    }))
                                                                                                            .add(Separator())
                                                                                                            .add(
                                                                                                                MenuItem("Remove Menu")
                                                                                                                    .accelerator(CTRL + "M")
                                                                                                                    .onClick({() => menuBar.clear()})
                                                                                                                )
                                                                                                        )
                                                                                                    .add(Menu("Menu 2"))
                                                                                                    .add(Menu("Menu 3"))
                                                                                            }))
                                                                            }))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("GridPanel", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test GridPanel", "Add buttons and clear them.",
                                                                            {
                                                                                var margin = 30
                                                                                val dp = GridPanel().lineBorder(java.awt.Color.BLACK).margin({() => margin})
                                                                                BorderPanel()
                                                                                    .height(_ => 200)
                                                                                    .width(_ => 500)
                                                                                    .center(dp)
                                                                                    .south(
                                                                                        TwoColumnPanel()
                                                                                            .add("Add", Button("Add").onClick({() => dp.add(Button("New"))}))
                                                                                            .add("Clear", Button("Clear").onClick({() => dp.clear()}))
                                                                                            .add("Margin", Slider().value({() => margin}).value.update({(x : Int) => margin = x}))
                                                                                            .add("Margin", 
                                                                                                Spinner()
                                                                                                    .value({() => margin})
                                                                                                    .value.update({(x : Int) => margin = x})
                                                                                                    .minimum({() => 0})
                                                                                                    .maximum({() => 100})
                                                                                                    ))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("ComboBox", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test ComboBox", "Select Items",
                                                                            {
                                                                                var selected = "Acht"
                                                                                var cijfers = List[String]("Nul","Een","Twee","Drie","Vier","Vijf","Zes","Zeven","Acht","Negen")
                                                                                var cijfersNL = List[String]("Nul","Een","Twee","Drie","Vier","Vijf","Zes","Zeven","Acht","Negen")
                                                                                var cijfersD = List[String]("Nul","Eins","Zwei","Drei","Vier","Fünf","Sechs","Sieben","Acht","Neun")
                                                                                BorderPanel()
                                                                                    .height(_ => 200)
                                                                                    .width(_ => 500)
                                                                                    .center(
                                                                                        TwoColumnPanel()
                                                                                            .add("Language: ", 
                                                                                                TextField()
                                                                                                    .content({() => selected})
                                                                                                    .content.update({(x : String) => selected = x}))
                                                                                            .add("Language: ", 
                                                                                                ComboBox()
                                                                                                    .content({() => selected})
                                                                                                    .content.update({(x : String) => selected = x})
                                                                                                    .items({() => cijfers}))
                                                                                            .add("Language: ", 
                                                                                                ComboBox()
                                                                                                    .content({() => selected})
                                                                                                    .content.update({(x : String) => selected = x})
                                                                                                    .items({() => cijfers}))
                                                                                            )
                                                                                    .south(
                                                                                        DistributedPanel().horizontal()
                                                                                            .add(Button("NL").onClick({() => cijfers = cijfersNL}))
                                                                                            .add(Button("Clear").onClick({() => cijfers = List[String]()}))
                                                                                            .add(Button("D").onClick({() => cijfers = cijfersD})))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("ListBox", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test ListBox", "Select Items",
                                                                            {
                                                                                var selected = List[String]("Vier","Zes","Acht")
                                                                                var cijfers = List[String]("Nul","Een","Twee","Drie","Vier","Vijf","Zes","Zeven","Acht","Negen")
                                                                                var cijfersNL = List[String]("Nul","Een","Twee","Drie","Vier","Vijf","Zes","Zeven","Acht","Negen")
                                                                                var cijfersD = List[String]("Nul","Eins","Zwei","Drei","Vier","Fünf","Sechs","Sieben","Acht","Neun")
                                                                                BorderPanel()
                                                                                    .height(_ => 600)
                                                                                    .width(_ => 500)
                                                                                    .center(
                                                                                        TwoColumnPanel()
                                                                                            .add("Language: ", 
                                                                                                ListBox()
                                                                                                    .content({() => selected})
                                                                                                    .content.update({(x : List[String]) => selected = x})
                                                                                                    .items({() => cijfers}))
                                                                                            .add("Language: ", 
                                                                                                ListBox()
                                                                                                    .content({() => selected})
                                                                                                    .content.update({(x : List[String]) => selected = x})
                                                                                                    .items({() => cijfers}))
                                                                                            )
                                                                                    .south(
                                                                                        DistributedPanel().horizontal()
                                                                                            .add(Button("NL").onClick({() => cijfers = cijfersNL}))
                                                                                            .add(Button("Clear").onClick({() => cijfers = List[String]()}))
                                                                                            .add(
                                                                                                Button("Move Up")
                                                                                                    .onClick(
                                                                                                        {() => 
                                                                                                            selected
                                                                                                                .take(1)
                                                                                                                .foreach(
                                                                                                                    {x => 
                                                                                                                        val i = cijfers.indexOf(x)
                                                                                                                        cijfers = Lists.move(i, i -1, cijfers)
                                                                                                                    })
                                                                                                        })
                                                                                                    )
                                                                                            .add(
                                                                                                Button("Move Down")
                                                                                                    .onClick(
                                                                                                        {() => 
                                                                                                            selected
                                                                                                                .take(1)
                                                                                                                .foreach(
                                                                                                                    {x => 
                                                                                                                        val i = cijfers.indexOf(x)
                                                                                                                        cijfers = Lists.move(i, i + 1, cijfers)
                                                                                                                    })
                                                                                                        })
                                                                                                    )
                                                                                            .add(Button("D").onClick({() => cijfers = cijfersD})))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("Keys", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(CTRL + "A" <= CTRL + "AB")
                                                                    Acid(
                                                                        TestDialog("Test Keys", "Press CTRL ABC",
                                                                            {
                                                                                val buttonA = Button("CTRL A")
                                                                                val buttonAB = Button("CTRL A B")
                                                                                val buttonABC = Button("CTRL A B C")
                                                                                TwoColumnPanel()
                                                                                    .keyAction(CTRL + "A", {() => buttonA.caption("WRONG!!!")})
                                                                                    .keyAction(CTRL + "AB", {() => buttonAB.caption("WRONG!!!")})
                                                                                    .keyAction(CTRL + "ABC", {() => buttonABC.caption("RIGHT")})
                                                                                    .add("CTRL + A", buttonA)
                                                                                    .add("CTRL + A B", buttonAB)
                                                                                    .add("CTRL + A B C", buttonABC)
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("Colors", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(CTRL + "A" <= CTRL + "AB")
                                                                    Acid(
                                                                        TestDialog("Test Keys", "Press CTRL ABC",
                                                                            {
                                                                                var color = java.awt.Color.GREEN
                                                                                TwoColumnPanel()
                                                                                    .add("Color", ColorPicker({() => color},{(x : java.awt.Color) => color = x}))
                                                                                    .add("Color", ColorPicker({() => color},{(x : java.awt.Color) => color = x}))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("Drag and drop", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    val nButton = Button("Drag to West")
                                                                    val wButton = Button("West")
                                                                    val cButton = Button("Drag with CTRL to East")
                                                                    val eButton = Button("East")
                                                                    val sButton = Button("Nothing can be dropped here")
    
                                                                    nButton
                                                                        .onStartDrag({() => Some(nButton)})
                                                                        .dropCondition({x => x.item == wButton})
                                                                        .onDrop(
                                                                            {_ =>
                                                                                nButton.caption("Ok")
                                                                                wButton.caption("Ok")
                                                                            })
                                                                    wButton
                                                                        .onStartDrag({() => Some(wButton)})
                                                                        .dropCondition({x => x.item == nButton})
                                                                        .onDrop(
                                                                            {_ => 
                                                                                wButton.caption("Drag to North")
                                                                                nButton.caption("North")
                                                                            })
                                                                    cButton
                                                                        .onStartDrag({() => Some(cButton)})
                                                                        .dropCondition({x => x.item == eButton && x.ctrlShiftPressed})
                                                                        .onDrop(
                                                                            {x =>
                                                                                eButton.caption("Ok")
                                                                                cButton.caption("Ok")
                                                                            })
                                                                    eButton
                                                                        .onStartDrag({() => Some(eButton)})
                                                                        .dropCondition({x => x.item == cButton && x.ctrlPressed})
                                                                        .onDrop(
                                                                            {_ =>
                                                                                eButton.caption("Drag to center with CTRL + SHIFT Pressed")
                                                                                cButton.caption("Center")
                                                                            })
                                                                    Acid(
                                                                        TestDialog("Test Drag and drop", "Follow instructions on buttons",
                                                                            {
                                                                                BorderPanel()
                                                                                    .height({_ => 400})
                                                                                    .width({_ => 600})
                                                                                    .north(nButton)
                                                                                    .west(wButton)
                                                                                    .center(cButton)
                                                                                    .east(eButton)
                                                                                    .south(sButton)
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("ScrollBar", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    var position = 50
                                                                    Acid(
                                                                        TestDialog("Test ScrollBar", "Move sliders.",
                                                                            BorderPanel()
                                                                                .north(ScrollBar().horizontal().value({() => position}).value.update({(x : Int) => position = x}))
                                                                                .west(ScrollBar().vertical().value({() => position}).value.update({(x : Int) => position = x}))
                                                                                .center(Label(" ").margin(300))
                                                                                .east(ScrollBar().vertical().value({() => position}).value.update({(x : Int) => position = x}))
                                                                                .south(ScrollBar().horizontal().value({() => position}).value.update({(x : Int) => position = x})),
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("Popup", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    var position = 50
                                                                    Acid(
                                                                        TestDialog("Test Popup", "Button should have popup",
                                                                            Button("Has Popup")
                                                                                .popup(
                                                                                        List(
                                                                                                 MenuItem("Item 1")
                                                                                                ,MenuItem("Item 2")
                                                                                                ,MenuItem("Item 3")
                                                                                                ,Separator()
                                                                                                ,MenuItem("Item 4")
                                                                                            ) 
                                                                                    ),
                                                                            {_ =>}))       
                                                                    counter == Acid.failed()
                                                            }),
                                                    TestSet("SplitPanel", 
                                                            {() => 
                                                                    val counter = Acid.failed()
                                                                    Acid(
                                                                        TestDialog("Test SplitPanel", "SplitPanel",
                                                                            {
                                                                                var splitPosition = 100
                                                                                var horizontal = true
                                                                                val lb1 = BorderPanel().center(Button("Left 1"))
                                                                                val rb1 = BorderPanel().center(Button("Right 1"))
                                                                                val lb2 = BorderPanel().center(Button("Left 2"))
                                                                                val rb2 = BorderPanel().center(Button("Right 2"))
                                                                                var vl = true
                                                                                var vr = true
                                                                                BorderPanel()
                                                                                    .north(
                                                                                        SplitPanel()
                                                                                            .horizontalDirection({() => horizontal})
                                                                                            .sliderPosition({() => splitPosition})
                                                                                            .sliderPosition.update({(x : Int) => splitPosition = x})
                                                                                            .height({_ => 150})
                                                                                            .width({_ => 300})
                                                                                            .left({() => if (!vl) null else lb1})
                                                                                            .right({() => if (!vr) null else rb1}))
                                                                                    .center(
                                                                                        TwoColumnPanel()
                                                                                            .add("Slider", Spinner().value({() => splitPosition}).value.update({(x : Int) => splitPosition = x}))
                                                                                            .add("Horizontal", CheckBox({() => horizontal},{(_x : Boolean) => horizontal = _x}))
                                                                                            .add("Left Visible", CheckBox({() => vl},{(_x : Boolean) => vl = _x}))
                                                                                            .add("Right Visible", CheckBox({() => vr},{(_x : Boolean) => vr = _x})))
                                                                                    .south(
                                                                                        SplitPanel()
                                                                                            .horizontalDirection({() => horizontal})
                                                                                            .sliderPosition({() => splitPosition})
                                                                                            .sliderPosition.update({(x : Int) => splitPosition = x})
                                                                                            .height({_ => 150})
                                                                                            .width({_ => 300})
                                                                                            .left({() => if (!vl) null else lb2})
                                                                                            .right({() => if (!vr) null else rb2}))
                                                                            },
                                                                            {_ =>}))
                                                                    counter == Acid.failed()
                                                            })
                                                )
                                        Dialog(
                                            {dialog =>
                                                
                                                val panel = GridPanel()
                                                var stamp = 1L
                                                var counter = 1
                                                testSets
                                                    .foreach(
                                                        {t => 
                                                            var lstamp = 0L
                                                            panel.keyAction(KeyManager.sequence("ctrl " + counter.toString.map({x => "Numpad" + x}).mkString(" ")), {() => if (t.action()) lstamp = System.currentTimeMillis})
                                                            panel.add(
                                                                {
                                                                    Button(counter + ". " + t.name)
                                                                        .onClick({() => if (t.action()) lstamp = System.currentTimeMillis})
                                                                        .enabled({() => lstamp < stamp})
                                                                })
                                                            counter += 1
                                                        })
                                                dialog
                                                    .menu(
                                                        MenuBar()
                                                            .add(
                                                                    Menu("Menu")
                                                                        .add(
                                                                                MenuItem("Clear")
                                                                                    .accelerator(CTRL + "C")
                                                                                    .onClick(
                                                                                        {() => 
                                                                                            Acid.clear()
                                                                                            stamp = System.currentTimeMillis
                                                                                        })
                                                                            )
                                                                ))
                                                    .caption("Testing Rock")
                                                    .keyAction(VK_Escape, {() => dialog.close()})
                                                    .add(panel)
                                                    .show()
                                            })
    
                                        WindowsRefresher.close()
                                    }
                            }
                }
    }
    
    