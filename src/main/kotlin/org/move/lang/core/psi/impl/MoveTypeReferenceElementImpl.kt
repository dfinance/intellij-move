package org.move.lang.core.psi.impl

import com.intellij.lang.ASTNode
import org.move.lang.core.psi.MoveTypeReferenceElement
import org.move.lang.core.resolve.ref.MoveReference
import org.move.lang.core.resolve.ref.MoveReferenceImpl
import org.move.lang.core.resolve.ref.MoveReferenceKind

abstract class MoveTypeReferenceElementImpl(node: ASTNode) : MoveReferenceElementImpl(node),
                                                             MoveTypeReferenceElement {
    override fun getReference(): MoveReference =
        MoveReferenceImpl(this, MoveReferenceKind.TYPE)
}