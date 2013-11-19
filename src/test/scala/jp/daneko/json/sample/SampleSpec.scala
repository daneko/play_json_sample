package jp.daneko.json.sample

import org.specs2._

import play.api.libs.json._
import play.api.libs.functional.syntax._

class Sample extends Specification {

  def is = s2"""
sampleとして次のようなjsonがある場合
<pre><code class="prettyprint">
${Json.prettyPrint(js)}
</code></pre>

case class にバインドできる $e

"""
  val sampleJsonStr = """
                        |{
                        |  "a" : [
                        |    { "aa":1, "bb":2, "cc":3 },
                        |    { "aa":2, "cc":4},
                        |    { "aa":3 }
                        |  ],
                        |  "b" : 1,
                        |  "c" : 2,
                        |  "d" : 3,
                        |  "e" : 4,
                        |  "f" : 5,
                        |  "g" : 6,
                        |  "h" : 7,
                        |  "i" : 8,
                        |  "j" : 9,
                        |  "k" : 10,
                        |  "l" : 11,
                        |  "m" : 12,
                        |  "n" : 13,
                        |  "o" : 14,
                        |  "p" : 15,
                        |  "q" : 16,
                        |  "r" : 17,
                        |  "s" : 18,
                        |  "t" : 19,
                        |  "u" : 20,
                        |  "v" : 21,
                        |  "w" : 22,
                        |  "x" : 23,
                        |  "y" : 24,
                        |  "z" : 25
                        |}
                        |""".stripMargin

  val js = Json.parse(sampleJsonStr)

  case class A(aa: Int, bb: Option[Int], cc: Option[Int])
  case class AN(
    a: List[A],
    b: Int,
    c: Int,
    d: Int,
    e: Int,
    f: Int,
    g: Int,
    h: Int,
    i: Int,
    j: Int,
    k: Int,
    l: Int,
    m: Int,
    n: Int)
  case class OZ(
    o: Int,
    p: Int,
    q: Int,
    r: Int,
    s: Int,
    t: Int,
    u: Int,
    v: Int,
    w: Int,
    x: Int,
    y: Int,
    z: Int)

  case class AZ(an: AN, oz: OZ)

  implicit val reada = Json.reads[A]

  val readaz = ((
    (__ \ 'a).read[List[A]] ~
    (__ \ 'b).read[Int] ~
    (__ \ 'c).read[Int] ~
    (__ \ 'd).read[Int] ~
    (__ \ 'e).read[Int] ~
    (__ \ 'f).read[Int] ~
    (__ \ 'g).read[Int] ~
    (__ \ 'h).read[Int] ~
    (__ \ 'i).read[Int] ~
    (__ \ 'j).read[Int] ~
    (__ \ 'k).read[Int] ~
    (__ \ 'l).read[Int] ~
    (__ \ 'm).read[Int] ~
    (__ \ 'n).read[Int]
  ).apply(AN.apply _) and (
      (__ \ 'o).read[Int] ~
      (__ \ 'p).read[Int] ~
      (__ \ 'q).read[Int] ~
      (__ \ 'r).read[Int] ~
      (__ \ 's).read[Int] ~
      (__ \ 't).read[Int] ~
      (__ \ 'u).read[Int] ~
      (__ \ 'v).read[Int] ~
      (__ \ 'w).read[Int] ~
      (__ \ 'x).read[Int] ~
      (__ \ 'y).read[Int] ~
      (__ \ 'z).read[Int]
    ).apply(OZ.apply _))(AZ.apply _)

  def e = {
    val res = js.validate[AZ](readaz)
    (res.asOpt must beSome).updateMessage(s => s + " : bindできてない").orThrow
    (res.asOpt.get.oz.z === 25).updateMessage(s => s + " : 一体なにをbindしたのかね?").orThrow
  }
}

