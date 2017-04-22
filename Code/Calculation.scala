package nl.datakneder.temp
    {
        import scala.language.implicitConversions
        import scala.language.reflectiveCalls
        import nl.datakneder.core.Utils.Framework._
        
        object Calculation
            {
                object Clock
                    {
                        private var __stamp = 0L
                        def apply() = __stamp
                        def next() : this.type = Reflect(__stamp += 1, this)
                    }
                    
                trait iHasCalculation[A]
                    {
                        def Calculation : iCalculation[A]
                    }
                trait iCalculation[A]
                    extends iHasCalculation[A]
                        {
                            def Calculation = this  
                            def apply() : A
                            def apply(_x : Long) : A
                            def apply(_x : Long => A, _d : iHasCalculation[_]*) : this.type
                            def isDirty(_x : Long) : Boolean
                            def dependencies() : List[iHasCalculation[_]] 
                            def dependencies(_x : List[iHasCalculation[_]]) : this.type 
                            def dependencies(_x : iHasCalculation[_]*) : this.type = dependencies(_x.toList) 
                        }
                        
                implicit def InjectOptionLong(_x : Option[Long]) =
                    new Object
                        {
                            def isDirty(_y : Long) : Boolean = _x == None || _x.get < _y
                        }
                class Calculation[A](_x : Long => A)
                    extends iCalculation[A]
                        {
                            // Calculation
                                private var __calculation : Long => A = _x
                                def assign(_x : A) : this.type = apply({_ => _x})
                                def apply(_x : Long => A, _d : iHasCalculation[_]*) : this.type = 
                                    Reflect(
                                        {
                                            __calculation = _x
                                            Clock.next()
                                            __value = None
                                            __inspectionStamp = None
                                            __calculationStamp = None
                                            __dependencies = _d.toList
                                        }, this)
                            // Dependencies
                                private var __dependencies : List[iHasCalculation[_]] = List()
                                def dependencies() : List[iHasCalculation[_]] = __dependencies
                                def dependencies(_x : List[iHasCalculation[_]]) : this.type = Reflect({__dependencies = _x}, this)
                                def isCalculated() : Boolean = __dependencies.size > 0
                            // Stamps
                                private var __calculationStamp : Option[Long] = None
                                private var __inspectionStamp : Option[Long] = None
                                def isDirty(_x : Long) : Boolean = 
                                    {
                                        __inspectionStamp
                                            .map({s => _x <= s})
                                            .getOrElse(true)
                                    }
                            // Value
                                private var __value : Option[A] = None
                                def value(_x : Long) : Option[A] = 
                                    {
                                        //System.out.println("A")
                                        if (__inspectionStamp.isDirty(_x))
                                            {
                                                //System.out.println("B")
                                                __inspectionStamp = Some(_x)
                                                if (__value == None || __dependencies.iterator.filter(_.Calculation.isDirty(_x)).hasNext)
                                                    {
                                                        //System.out.println("C")
                                                        __value = TryCatch({Some(__calculation(_x))}, None)
                                                    }
                                            }
                                        __value
                                    }
                                def apply() : A = value(Clock()).get
                                def apply(_x : Long) : A = value(_x).get
                                
                        }
                class OptionCalculation[A]()
                    extends Calculation[Option[A]]({_ => None})
                        {
                            def assign(_x : A) : this.type = apply({_ => Some(_x)})
                        }
                class ListCalculation[A]()
                    extends Calculation[List[A]]({_ => List[A]()})
                        {
                            def clear() : this.type = assign(List[A]())
                            def size() : Int = apply().size
                            def size(_n : Long) : Int = apply(_n).size
                            def add(_x : A*) : this.type = Reflect({assign(apply() ++ _x.toList)}, this)
                            def remove(_x : A*) : this.type = Reflect({assign(apply().filter(!_x.contains(_)))}, this)
                        }
            }
    }