package com.jdevelop.ubs.flmviewer.ui.render

import java.awt.Component
import javax.swing.table.TableCellRenderer
import javax.swing.JLabel
import javax.swing.SwingConstants
import java.awt.Color

class DefaultCellRenderer extends JLabel with TableCellRenderer {
	
	val backgroundTitle = Color.GRAY
	
	val backgroundNormal = Color.WHITE
	
	val backgroundActive = Color.GRAY.brighter
	
	setHorizontalAlignment(SwingConstants.CENTER)
	setOpaque(true)
	
	override def getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, colIndex) : Component = {
		rowIndex match {
			case 0 => setBackground(backgroundTitle)
			case _ => 
				isSelected match {
					case true  => setBackground(backgroundActive)
					case false => setBackground(backgroundNormal)
				}
		}
		setText(value.toString)
		this
	}
	
}