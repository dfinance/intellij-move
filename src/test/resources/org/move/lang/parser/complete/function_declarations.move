module M {
    fun fn(): u8 {}

    public fun fn(): u8 {}

    public(script) fun fn(): u8 {}

    public(friend) fun fn(): u8 {}

    fun fn_with_returned_tuple(): (u8, u8) {}

    fun fn_with_type_params<T: store, U: store + drop>(): T {}

    fun fn_with_signer(s: &signer) {}

    fun fn_with_borrowed_qual_type(s: &Transaction::Sender) {}

    fun fn_with_arguments(a: vector<T>, b: u8, c: Transaction::Sender): 0x1::Transaction::Sender {}

    fun fn_with_acquires()
        acquires vector<T>, T, Transaction::Sender {}

    native fun native_fn(a: vector<u8>): u8;

    native public fun native_fn(a: vector<u8>): u8;

    native public(script) fun native_fn(a: vector<u8>): u8;

    native public(friend) fun native_fn(a: vector<u8>): u8;

    native fun native_fn_with_acquires() acquires T;
}
