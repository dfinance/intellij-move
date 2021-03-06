package org.move.lang.core.psi.mixins

import com.intellij.lang.ASTNode
import org.move.lang.core.psi.MoveStructSpecDef
import org.move.lang.core.psi.impl.MoveReferenceElementImpl
import org.move.lang.core.resolve.ref.MoveReference
import org.move.lang.core.resolve.ref.MoveReferenceImpl
import org.move.lang.core.resolve.ref.Namespace

abstract class MoveStructSpecMixin(node: ASTNode) : MoveReferenceElementImpl(node),
                                                    MoveStructSpecDef {
    override fun getReference(): MoveReference =
        MoveReferenceImpl(this, Namespace.TYPE)

}
