package org.move.ide.intentions

import org.move.utils.tests.MoveIntentionTestCase

class AddAcquiresIntentionTest: MoveIntentionTestCase(AddAcquiresIntention::class) {
    fun `test unavailable on simple function`() = doUnavailableTest("""
    module M {
        fun main() {
            call/*caret*/();
        }
    }    
    """)

    fun `test unavailable on move_from without type argument`() = doUnavailableTest("""
    module M {
        struct Loan has key {}
        fun main() {
            move_from/*caret*/(0x1);
        }
    }    
    """)

    fun `test unavailable if unresolved type`() = doUnavailableTest("""
    module M {
        fun main() {
            move_from<Loan>/*caret*/(0x1);
        }
    }    
    """)

    fun `test unavailable on move_from with proper acquires type`() = doUnavailableTest("""
    module M {
        struct Loan has key {}
        fun main() acquires Loan {
            move_from<Loan>/*caret*/(0x1);
        }
    }    
    """)

    fun `test available on move_from without acquires`() = doAvailableTest("""
    module M {
        struct Loan has key {}
        fun main() {
            move_from<Loan>/*caret*/(0x1);
        }
    }    
    """, """
    module M {
        struct Loan has key {}
        fun main() acquires Loan {
            move_from<Loan>/*caret*/(0x1);
        }
    }    
    """)

//    fun `test available on move_from fully qualified path`() = doAvailableTest("""
//    module M {
//        struct Loan has key {}
//        fun main() {
//            move_from<0x1::M::Loan>/*caret*/(0x1);
//        }
//    }
//    """, """
//    module M {
//        struct Loan has key {}
//        fun main() acquires 0x1::M::Loan {
//            move_from<0x1::M::Loan>/*caret*/(0x1);
//        }
//    }
//    """)

    fun `test available on move_from with different acquires`() = doAvailableTest("""
    module M {
        struct Loan has key {}
        struct Deal has key {}
        fun main() acquires Deal {
            move_from<Loan>/*caret*/(0x1);
        }
    }    
    """, """
    module M {
        struct Loan has key {}
        struct Deal has key {}
        fun main() acquires Deal, Loan {
            move_from<Loan>/*caret*/(0x1);
        }
    }    
    """)
}
