package org.move.ide

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.move.lang.MoveElementTypes.*
import org.move.lang.core.MOVE_KEYWORDS
import org.move.lang.core.lexer.createMoveLexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default

class MoveHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = createMoveLexer()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        val color = when (tokenType) {
            IDENTIFIER -> Default.IDENTIFIER

            BLOCK_COMMENT -> Default.BLOCK_COMMENT
            LINE_COMMENT -> Default.LINE_COMMENT

            L_PAREN, R_PAREN -> Default.PARENTHESES
            L_BRACE, R_BRACE -> Default.BRACES
            L_BRACK, R_BRACK -> Default.BRACKETS

            SEMICOLON -> Default.SEMICOLON
            DOT -> Default.DOT
            COMMA -> Default.COMMA

            BYTE_STRING_LITERAL -> Default.STRING
            INTEGER_LITERAL, ADDRESS_LITERAL -> Default.NUMBER

            BOOL_LITERAL -> Default.KEYWORD
            in MOVE_KEYWORDS -> Default.KEYWORD

            else -> null
        }
        return pack(color)
    }
}
