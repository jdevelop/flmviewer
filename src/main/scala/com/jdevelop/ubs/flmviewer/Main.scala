package com.jdevelop.ubs.flmviewer

import scala.swing.MainFrame
import scala.swing.SimpleSwingApplication
import com.jdevelop.ubs.flmviewer.ui.FLMPanel
import javax.swing.JFrame

object Main extends SimpleSwingApplication {
	
	override def top = new MainFrame {
		contents = new FLMPanel();
	}
}