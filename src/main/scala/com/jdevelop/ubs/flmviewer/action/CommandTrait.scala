package com.jdevelop.ubs.flmviewer.action

trait CommandTrait[T] {

  def perform(context: T): Unit;

}