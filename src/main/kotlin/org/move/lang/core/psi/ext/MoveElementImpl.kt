package org.move.lang.core.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class MoveElementImpl(node: ASTNode): ASTWrapperPsiElement(node)