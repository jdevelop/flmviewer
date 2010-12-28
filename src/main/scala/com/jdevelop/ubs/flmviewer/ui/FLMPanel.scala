package com.jdevelop.ubs.flmviewer.ui

import scala.swing.Button
import scala.swing.FlowPanel
import scala.swing.Alignment
import scala.swing.Label
import scala.swing.Table
import scala.swing.Component
import scala.swing.BorderPanel
import javax.swing.table.AbstractTableModel
import java.awt.Color
import com.jdevelop.ubs.flmviewer.action._
import com.jdevelop.ubs.flmviewer.data.MessageField
import com.jdevelop.ubs.flmviewer.ui.render._

class FLMPanel extends BorderPanel {

  private val tableDataModel = new AbstractTableModel() {

    var modelData: Array[MessageField] = Array(new MessageField("1", "2"), new MessageField("3", "4"), new MessageField("5", "6"));

    def getRowCount = modelData.length + 1

    def getColumnCount = 2

    def getValueAt(row: Int, column: Int): Object = {
      if (row > modelData.length || column > 1)
        return null;
      val msgField = row match {
        case 0 => new MessageField("Field name", "Field value")
        case _ => modelData(row - 1)
      }
      return column match {
        case 0 => msgField fieldName
        case 1 => msgField fieldValue
        case _ => null
      }
    }

    def updateModelData(newData: Array[MessageField]) {
      modelData = newData
      fireTableDataChanged()
    }

  }

  val table = new Table() {
    
  	model = tableDataModel
    
  	opaque = true

    private class TableHeading(caption: String) extends Label(caption) {
      horizontalAlignment = Alignment.Center
      background = Color.GRAY.brighter
      opaque = true
    }

    private class TableBody(text: String, _background: Color = Color.WHITE) extends Label(text) {
      horizontalAlignment = Alignment.Center
      background = _background
      opaque = true
    }

    private val labelFieldName = new TableHeading("Field name");

    private val labelFieldVal = new TableHeading("Field value");

    private val selectedBodyColor = Color.GRAY.brighter

    private val defaultBodyColor = Color.WHITE

    override protected def rendererComponent(isSelected: Boolean, focused: Boolean, row: Int, col: Int): Component = {
      row match {
        case 0 =>
          col match {
            case 0 => labelFieldName
            case 1 => labelFieldVal
          }
        case _ =>
          if (row <= rowCount) {
            val cellValue = model.getValueAt(row, col).asInstanceOf[String]
            val color = isSelected match {
              case true =>
                selectedBodyColor
              case false =>
                defaultBodyColor
            }
            new TableBody(cellValue, color)
          } else {
            null
          }
      }
    }
  }

  private val columnModel = table.peer.getColumnModel()
  columnModel.getColumn(0).setCellRenderer(new DefaultCellRenderer());
  columnModel.getColumn(1).setCellRenderer(new DefaultCellRenderer());

  private val label = new Label("Fields") {
    horizontalAlignment = Alignment.Center
  }

  private val actionPanel = new FlowPanel(new Button("Load"), new Button("Reload"), new Button("Save"), new Button("Save as..."))

  add(actionPanel, BorderPanel.Position.South)
  add(table, BorderPanel.Position.Center)
  add(label, BorderPanel.Position.North)

  def updateTableData(tableData: Array[MessageField]) {
    tableDataModel.updateModelData(tableData)
  }

}