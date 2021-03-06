package org.move.lang.core.stubs.index

import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey
import org.move.lang.core.psi.MoveNamedElement
import org.move.lang.core.stubs.MoveFileStub

class MoveNamedElementIndex : StringStubIndexExtension<MoveNamedElement>() {
    override fun getVersion(): Int = MoveFileStub.Type.stubVersion

    override fun getKey(): StubIndexKey<String, MoveNamedElement> = KEY

    companion object {
        val KEY: StubIndexKey<String, MoveNamedElement> =
            StubIndexKey.createIndexKey("org.move.lang.core.stubs.index.MoveNamedElementIndex")

//        fun findElementsByName(
//            project: Project,
//            target: String,
//            scope: GlobalSearchScope = GlobalSearchScope.allScope(project),
//        ): Collection<MoveNamedElement> {
//            checkCommitIsNotInProgress(project)
//            return getElements(KEY, target, project, scope)
//        }
    }
}