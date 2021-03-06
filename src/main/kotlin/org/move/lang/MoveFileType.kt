package org.move.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import org.move.ide.MoveIcons
import javax.swing.Icon

object MoveFileType : LanguageFileType(MoveLanguage) {
    override fun getIcon(): Icon? = MoveIcons.MOVE
    override fun getName(): String = "Move"
    override fun getDefaultExtension(): String = "move"
    override fun getDescription(): String = "Move Language file"
}