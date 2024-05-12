(module
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(start $init)
(global $SP (mut i32) (i32.const 0))          ;; start of stack
(global $MP (mut i32) (i32.const 0))          ;; mark pointer
(global $NP (mut i32) (i32.const 131071996))  ;; heap 2000*64*1024-4
(global $swap (mut i32) (i32.const 0))
(global $trash (mut i32) (i32.const 0))
(global $darr (mut i32) (i32.const 0))
(func $init
    i32.const 20
    call $reserveStack
    call $setDynamicLink
    call $0
    call $freeStack
    global.set $trash
)
(func $reserveStack (param $size i32)
    (result i32)
    global.get $MP
    global.get $SP
    global.set $MP
    global.get $SP
    local.get $size
    i32.add
    global.set $SP
    global.get $SP
    global.get $NP
    i32.gt_u
    if
        i32.const 3
        call $exception
    end
)
(func $freeStack (type $_sig_void)
   global.get $MP
   global.set $SP
   global.get $MP
   i32.load
   global.set $MP
)
(func $setDynamicLink
(param $dynamicLink i32)
    global.get $MP
    local.get $dynamicLink
    i32.store
)
(func $allocate_heap
(param $size i32)
(result i32)
    (local $ret_addr i32)
    global.get $NP
    local.get $size
    i32.sub
    global.set $NP ;; we have brought the NP back size bytes
    global.get $SP
    global.get $NP
    i32.gt_u
    if
        i32.const 3
        call $exception ;; the SP has passed the NP
    end
    global.get $NP
    i32.const 4
    i32.add ;; NP + 4 is the position to be occupied now, so we leave it in the stack
)
(func $allocate_stack
(param $size i32)
(result i32)
    (local $ret_addr i32)
    global.get $SP
    local.set $ret_addr ;; we save the old SP value in the ret_addr

    global.get $SP
    local.get $size
    i32.add
    global.set $SP ;; we have brought the SP forward size bytes

    global.get $SP
    global.get $NP
    i32.gt_u
    if
        i32.const 3
        call $exception ;; the SP has passed the NP
    end

    local.get $ret_addr ;; we save the old SP at the top of the stack
)
(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
    (param $src i32)
    (param $dest i32)
    (param $n i32)
    block
        loop
        local.get $n
        i32.eqz
        br_if 1
        local.get $n
        i32.const 1
        i32.sub
        local.set $n
        local.get $dest
        local.get $src
        i32.load
        i32.store
        local.get $dest
        i32.const 4
        i32.add
        local.set $dest
        local.get $src
        i32.const 4
        i32.add
        local.set $src
        br 0
        end
    end
)
(func $exponentiation (param $base i32) (param $exponent i32) (param $modulus i32) (result i32)
    (local $result i32)
    (local $baseSquared i32)
    (local $exponentCopy i32)
    (local $modulusCopy i32)

    ;; Inicializar variables locales
    (local.set $result (i32.const 1))
    (local.set $baseSquared (local.get $base))
    (local.set $exponentCopy (local.get $exponent))
    (local.set $modulusCopy (local.get $modulus))

    (block $endLoop
        (loop $mainLoop
            ;; Chequear si el exponente es cero
            ( if (i32.eqz (local.get $exponentCopy))
                (then
                    (br $endLoop)
                )
            )

            ;; Chequear si el exponente es impar
            (if (i32.and (local.get $exponentCopy) (i32.const 1))
                (then
                    ;; result = (result * base) % modulus
                    (local.set $result (i32.rem_s (i32.mul (local.get $result) (local.get $base)) (local.get $modulusCopy)))
                )
            )

            ;; exponent >>= 1 (Dividir el exponente por 2)
            (local.set $exponentCopy (i32.shr_s (local.get $exponentCopy) (i32.const 1)))

            ;; base = (base * base) % modulus
            (local.set $baseSquared (i32.rem_s (i32.mul (local.get $baseSquared) (local.get $baseSquared)) (local.get $modulusCopy)))

            ;; continue main loop
            (br $mainLoop)
        )
    )

    (local.get $result) ;; Poner el resultado en el stack
)
(func $1
    (result i32)
    i32.const 8
    global.get $MP
    i32.add
    i32.load
    i32.const 100
    i32.store
    i32.const 8
    global.get $MP
    i32.add
    i32.load
    i32.load
    call $print
    i32.const 0
)
(func $0
    (result i32)
    i32.const 8
    global.get $MP
    i32.add
    i32.const 1
    i32.store
    i32.const 8
    global.get $MP
    i32.add
    i32.load
    call $print
    i32.const 12
    global.get $MP
    i32.add
    i32.const 8
    global.get $SP
    i32.add
    i32.const 8
    global.get $MP
    i32.add
    i32.store
    i32.const 16
    call $reserveStack
    call $setDynamicLink
    global.get $MP
    i32.const 4
    i32.add
    i32.const 0
    i32.store
    call $1
    call $freeStack
    i32.store
    i32.const 8
    global.get $MP
    i32.add
    i32.load
    call $print
    i32.const 0
)
)