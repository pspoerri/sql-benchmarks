/*
 * Crail SQL Benchmarks
 *
 * Author: Animesh Trivedi <atr@zurich.ibm.com>
 *
 * Copyright (C) 2017, IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ibm.crail.benchmarks

object Main {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    println("concat arguments = " + foo(args))
    val options = new ParseOptions()
    options.parse(args)

    val test:SQLTest = SQLTestFactory.newTestInstance(options)
    val s = System.nanoTime()
    test.execute()
    val e = System.nanoTime()
    println("-------------------------------------------------")
    println("Test           : " + test.plainExplain())
    println("Action         : " + options.getAction.toString)
    println("Execution time : " + (e - s)/1000000 + " msec")
    if(options.getVerbose){
      println("---------------- explain ------------------------")
      println(test.explain())
    }
    println("-------------------------------------------------")
    test.stop
  }
}