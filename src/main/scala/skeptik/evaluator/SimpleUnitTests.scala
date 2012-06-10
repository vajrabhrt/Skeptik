package skeptik.evaluator

import skeptik.expression._
import skeptik.judgment._
import skeptik.proof._
import skeptik.expression.formula._
//import skeptik.expressionpositions._
//import skeptik.formulaAlgorithms._
import skeptik.proof.natural.{NamedE, ImpElim => ImpE, ImpIntro => ImpI, Assumption}
import skeptik.proof.natural.{ImpElimC, ImpIntroC}
import skeptik.exptype._

import skeptik.prover.SimpleProver
import skeptik.prover.SimpleProverWithSideEffects

import skeptik.proof.ProofNodeCollection

import skeptik.algorithm.generator.formulaGen._

import scala.collection.immutable.{HashSet => ISet}

object SimpleUnitTests {

  def main(args: Array[String]): Unit = {
    
    
//    val position1 = SubformulaP(Prop("A"),1)
//    val position2 = SubformulaP(Prop("A"),2)
//    val position3 = SubformulaP(Prop("A"),3)
//    val position4 = SubformulaP(Prop("A"),4)
//    val position5 = SubformulaP(Prop("A"),5)
//    
//    def testF : E => E = (e:E) => Var("Hi",o)
//
//    
//    val ha = Imp(Imp(Prop("A"),Prop("A")), Imp(Prop("A"),Prop("A")))
//    println( (testF @: position1)( ha ) )
//    println( (testF @: position2)( ha ) )
//    println( (testF @: position3)( ha ) )
//    println( (testF @: position4)( ha ) )
//    try {println( (testF @: position5)( ha ) )} catch {case e: InexistentPositionException => println(e)}
//    
//    println( position1 isPositiveIn ha)
//    println( position2 isPositiveIn ha)
//    println( position3 isPositiveIn ha)
//    println( position4 isPositiveIn ha)
//        
//    //println((testF @: SubformulaP(Prop("A"),1))( Imp(Prop("A"),Prop("B"))  ))
//    
//    //val input = "Testing Gridgain's mapreduce on this string."
//    
//    //val gridtest = new GridGainSeq(input.split(" "))
//
//    //gridtest.mapReduce((w: String) => {println(w); w.length}, (s: Seq[Int]) => s.sum)
//    
//    val prover = new SimpleProver(Seq(AndL, AxiomTaut, WeakeningL))
//    //val parprover = new SimpleGridGainProver(Seq(AndL, AxiomTaut, WeakeningL))
//    
//    val goal = Sequent(And(Var("A", o),Var("B",o))::Nil,Var("A", o))
//    println(prover.prove(goal).getOrElse("no proof"))
//    //println(parprover.prove(goal).getOrElse("no proof"))
//    
//
//    val ndProver = new SimpleProver(Seq(ImpElim,ImpIntro,NDAxiom))
//    val ndGoal = Sequent(Nil,Imp(Var("B", o),
//                                 Imp(Imp(Var("B", o),
//                                         Var("C", o)), 
//                                     Var("C", o))))
//    println(ndProver.prove(ndGoal).getOrElse("none"))
//    
//    println()
//    
    val ndProver1 = new SimpleProverWithSideEffects(Seq(ImpE,ImpI,Assumption))
    val ndcProver = new SimpleProverWithSideEffects(Seq(ImpElimC,ImpIntroC,Assumption))
    //val ndGoal1 = Imp(Var("B", o),
    //                  Imp(Imp(Var("B", o),
    //                          Var("C", o)), 
    //                      Var("C", o)))
    val context1 = new ISet[NamedE]
    //println(ndProver1.prove(ndGoal1,context1).getOrElse("none"))
    
    println()
    
    val goals = generate2(2,5)
    
    println()
    
    //goals.foreach(println)
    
    println()
    println("hello!")
    
//    println(generate(4))
//    println(generate(generate(3),1))
//    println(generate(generate(3),2))
//    println(generateAcc(generate(2),2))
    
    var yesCounter = 0
    var noCounter = 0
    var yesCCounter = 0
    var noCCounter = 0
    var cumulativeSize = 0
    var size = 0
    var cumulativeCSize = 0
    var cSize = 0
    for (goal <- goals) {
      System.gc()
      val proof = ndProver1.prove(goal,context1)
      val provable = proof match {
        case None => {noCounter += 1; "no"} 
        case Some(p) => {yesCounter += 1;
                         size = ProofNodeCollection(p).size
                         cumulativeSize += size
                         //println(size); 
                         //println(p); 
                         "yes"}
      }
      val deepProof = None // ndcProver.prove(goal,context1)
      val deepProvable = deepProof match {
        case None => {noCCounter += 1; "no"} 
//        case Some(p) => {
//            yesCCounter += 1; 
//            cSize = ProofNodeCollection(p).size
//            cumulativeCSize += cSize
//            //println(size); 
//            //println(p); 
//            "yes"}
      }
      if (true) println(yesCounter + " , " + noCounter + " , " + provable + " , " + size + " , " + cumulativeSize + " , " + deepProvable + " , " + cSize + " , " + cumulativeCSize + "  , goal:" + goal)
    }
    
    println("yes: " + yesCounter)
    println("no:" + noCounter)
    println("yesC: " + yesCCounter)
    println("noC:" + noCCounter)
    println("cumulativeSize: " + cumulativeSize)
    println("cumulativeCSize: " + cumulativeCSize)
    
    
    val testG = Imp(Var("B", o),
                    Var("B", o))
    //println(ndcProver.prove(testG, context1))
    
    
//    println("Lambda Terms:")
//    println()
//    
//    val e1 = App(Var("P", i -> o), Var("a", i))
//    val e2 = App(Var("P", i -> o), Var("a", i))
//    val e3 = Abs(Var("a", i), e2.copy)
//    
//    println("e1 :" + e1 )
//    println("e2 :" + e2 )
//    println("e3 :" + e3 )
//    
//    println()
//    println()
//    println("Syntatic Equality versus Object Equality:")
//    println()
//    println("e1 == e2 :" + e1 == e2)
//    println("e1 syntaticEquals e2 :" + (e1 == e2))
//    println("e1 =*= e2 :" + (e1 == e2))
//    println("e1 == e1.copy :" + e1 == e1.copy)
//    println("e1 =*= e1.copy :" + (e1 == e1.copy))
//    
//    println()
//    println()    
//    println("Formulas:")
//    println()
//    
//    val e = Atom(Var("Q", i -> (i -> o)), Var("a", i)::Var("b", i)::Nil)
//    println("e : " + e)
//    e match {
//      case Atom(p,l) => {println(p);println(l)}
//      case _ => println("error")
//    }
//    
//    
//    val f = And(e, e.copy)
//    val f2 = And(e, e)
//    println("f : " + f)
//    
//    
//    val Pa = App(Var("P", i -> o), Var("a", i))
//    def gA(x:E) = Pa
//    val g = deepApply(gA,f,1::Nil)
//    println("g : " + g)
//    
//    val h = All(g,Var("x",i),(2::2::Nil)::Nil)
//    println("h : " + h)
//    
//    val j = All(Pa, Var("v", i), Var("a", i))
//    println("j : " + j)
// 
//    
//    println()
//    println()   
//    println("Alpha Equality:")
//    println()
//    
//    def v(n:String, t:T) = Var(n,t)
//    
//    val k = All(v("x",i), App(v("P", i -> o), v("x",i)))
//    val l = All(v("y",i), App(v("P", i -> o), v("y",i)))
//    println("k : " + k)
//    println("l : " + l)
//    println("k == l :" + k == l)
//    println("k =*= l :" + (k == l))
//    println("k alphaEquals l :" + (k alphaEquals l))
//    println("k =+= l :" + (k =+= l))
//    
//    
//    
//    println()
//    println()   
//    println("Sequent Calculus Proofs:")
//    println()
//    
//    val a = App(Var("P", i -> o), Var("a", i))
//    val b = App(Var("P", i -> o), Var("b", i))
//    val c = App(Var("P", i -> o), Var("c", i))
//    val d = Atom(Var("P", i -> o), Var("d", i)::Nil)
//   
//    def S(l1:List[E],l2:List[E]) = Sequent(l1,l2)
//
//    
//    val p1 = AndL(AndR(Axiom(S(a::Nil,b::Nil)),
//                       Axiom(S(c::Nil,d::Nil)),
//                       b,
//                       d),
//                  a,
//                  c)
//    
//    val p2 = AllL(p1,p1.conclusion.ant(0),Var("v",i),(1::1::Nil)::Nil)    
//    val p3 = AllR(p2,p2.conclusion.suc(0),Var("z",i),Var("d", i))
//    
//    println(p3)
//
//    println()
//    println()
//    
//    val (a1,a2,b1,b2,c1,c2) = (a.copy,a.copy,b.copy,b.copy,c.copy,c.copy)
//    val p4 = CutIC(Axiom(S(a1::Nil,b1::c1::Nil)),
//                   Axiom(S(a2::b2::Nil,c2::Nil)),
//                   b1,
//                   b2)
//    println("p4: \n" + p4)
//
//    println()
//    println()
//    
//    val p5 = Cut(Axiom(S(a1::Nil,b1::c1::Nil)),
//                 Axiom(S(a2::b2::Nil,c2::Nil)),
//                 b1,
//                 b2)
//    println("p5: \n" + p5)
//    
//    
//    println()
//    println()   
//    println("Unification: ")
//    println()
//    
//    import skeptik.expression.algorithms._
//    
//    
//    object test2 {
//      def apply() = {
//        val z = Var("z",i->o)
//        val P = v("P",i->o)
//        val w = v("u",i)
//        implicit val uW : Set[Var] = Set(z)
//        println(unify((App(z,w),App(P,w.copy))::Nil))
//        println(unify((App(z,w),App(Abs(v("x",i),App(P,v("x",i))),w.copy))::Nil))
//      }   
//    }
//    test2()
//
//
//    
//    println()
//    println()   
//    println("Higher-order Resolution: ")
//    println()
//
//    
//    val hAiBC = Axiom(S(Atom(v("A",i->o),v("d",i)::Nil)::Nil, Atom(v("B",i->o),v("c",i)::Nil)::v("C",o)::Nil))
//    val hABi = Axiom(S(Atom(v("A",i->o),Var("y",i)::Nil)::Atom(v("B",i->o),Var("y",i)::Nil)::Nil, Nil))
//    val hCi = Axiom(S(Var("X",o)::Nil, Nil))
//    val hiA = Axiom(S(Nil, Atom(v("A",i->o),Var("z",i)::Nil)::Nil))
//    
//    
//    object test1 {
//      def apply() = {
//        implicit val uV = Set(Var("z",i), Var("y",i), Var("X",o))
//
//
//        val hrProof = R(R(hiA,
//                        R(hiA,
//                          R(hAiBC,hABi))),
//                      hCi)
//        println(hrProof)         
//      } 
//    }
//    test1()

    
//    val hrProof = R(R(hiA,
//                      R(hiA,
//                        R(hAiBC,hABi,uV),uV),uV),
//                    hCi,uV)
//    println(hrProof)
    
//    import skeptik.proofs.traversal._;
//    
//    val (up, down) = topologicallySort(hrProof)
//    println()
//    up.foreach(n => println(n.conclusion))
//    println()
//    down.foreach(n => println(n.conclusion))
//    println()
//    bottomUp[Sequent,Any](hrProof,(p,l)=>{println(p.conclusion)})
//    println()
//    topDown[Sequent,Any](hrProof,(p,l)=>{println(p.conclusion)})  
//    
//    val dir = "/Users/Bruno/Documents/proofs/"
//    val directory2 = dir + "SmallProofs/"
//    val proofFilesAim = List("50-1_6-no-1",
//                             "50-1_6-no-2",
//                             "50-1_6-no-3",
//                             "50-1_6-no-4",
//                             "50-2_0-no-1",
//                             "50-2_0-no-2",
//                             "50-2_0-no-3",
//                             "50-2_0-no-4"
//                          ).map(s => "aim-" + s)
//                  
//    import skeptik.parsers.{SimplePropositionalResolutionProofFormatParser => Parser}
//    for (proofFile <- proofFilesAim) {
//      println(proofFile)
//      println("parsing")
//      val parser = new Parser(directory2 + proofFile + ".proof")
//      val p = parser.getProof
//      println(p)
//    }  

  }

}
