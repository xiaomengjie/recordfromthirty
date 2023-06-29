import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataSet
import java.io.File
import java.io.FileOutputStream

fun main() {
    processTable()
}

private fun processTable(){
    val markdown = """
        | 姓名 | 年龄 | 城市 |
        | :- | :- | :- |
        | 张三 | 25 | 北京 |
        | 李四 | 30 | 上海 |
        | 王五 | 28 | 广州 |
    """.trimIndent()

    val options = MutableDataSet().set(Parser.EXTENSIONS, listOf(TablesExtension.create()))
    val parser = Parser.builder(options).build()
    val document: Node = parser.parse(File("../../english-words/words.md").readText())
//    val document: Node = parser.parse(File("./words.md").readText())
//    val document: Node = parser.parse(markdown)

    // 获取到markdown中的表格每一行的数据，通过nodeName==TableRow过滤
    val tableRows = document.descendants.filter {
        it.nodeName == "TableRow"
    }

    // 取出第一行的表格中的数据，通过nodeNane==TableCell过滤
    val headers = tableRows.first().descendants.filter { it.nodeName == "TableCell" }
        .map { it.childChars.toString().trim() }

    // 移除前两行数据，取出剩余表格中的所有数据，通过nodeNane==TableCell过滤
    val rows = tableRows.drop(2).map { row ->
        row.descendants.filter { it.nodeName == "TableCell" }
            .map { it.childChars.toString().trim() }
    }

    //对数据进行排序
    val sortedRows = rows.sortedBy { it[0].lowercase() } // 按照字母排序

    val sortedMarkdown = buildString {
        appendLine(headers.joinToString(" | ", "| ", " |"))
        appendLine(headers.joinToString("|", "|", "|") {
            if (it == "翻译") ":----------------" else ":----------------:"
        })
        sortedRows.forEach { row ->
            appendLine(row.joinToString(" | ", "| ", " |"))
        }
    }

    val fileOutputStream = FileOutputStream("../../english-words/words_ordered.md")
    fileOutputStream.write(sortedMarkdown.toByteArray())
}