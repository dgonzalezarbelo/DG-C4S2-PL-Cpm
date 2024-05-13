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
    i32.const 60
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
(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
    (param $src i32)
    (param $dest i32)
    (param $size i32)
    (local $n i32)
    local.get $size
    i32.const 4
    i32.div_s
    local.set $n
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
(func $exponentiation (param $base i32)(param $exponent i32)(result i32)
    (local $result i32)
    i32.const 1
    local.set $result
    block
    loop
        local.get $exponent
        i32.const 0
        i32.gt_u
        i32.eqz
        br_if 1
            local.get $base
            local.get $result
            i32.mul
            local.set  $result

            local.get $exponent
            i32.const 1
            i32.sub
            local.set $exponent
        br 0
    end
    end
    local.get $result
)
(func $1
    (result i32)
    global.get $MP
    i32.const 4
    i32.add
    i32.load
    i32.const 0
    i32.add
    i32.const 0
    i32.add
    i32.const 8
    global.get $MP
    i32.add
    i32.load
    i32.store
    global.get $MP
    i32.const 4
    i32.add
    i32.load
    i32.const 0
    i32.add
    i32.const 4
    i32.add
    i32.const 12
    global.get $MP
    i32.add
    i32.load
    i32.store
    global.get $MP
    i32.const 4
    i32.add
    i32.load
)
(func $2
    (result i32)
    global.get $MP
    i32.const 4
    i32.add
    i32.load
    i32.const 0
    i32.add
    i32.load
)
(func $0
    (result i32)
    i32.const 48
    global.get $MP
    i32.add
    i32.const 0
    i32.store
    block
    loop
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.const 5
    i32.lt_s
    i32.eqz
    br_if 1
    i32.const 8
    global.get $SP
    i32.add
    i32.const 10
    i32.const 2
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.mul
    i32.add
    i32.store
    i32.const 12
    global.get $SP
    i32.add
    i32.const 98234
    i32.store
    i32.const 24
    call $reserveStack
    call $setDynamicLink
    global.get $MP
    i32.const 4
    i32.add
    global.get $SP
    i32.const 8
    i32.sub
    i32.store
    call $1
    call $freeStack
    i32.const 8
    global.get $MP
    i32.add
    i32.const 8
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.mul
    i32.add
    i32.const 8
    call $copyn
    i32.const 48
    global.get $MP
    i32.add
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.const 1
    i32.add
    i32.store
    br 0
    end
    end
    i32.const 48
    global.get $MP
    i32.add
    i32.const 0
    i32.store
    i32.const 52
    global.get $MP
    i32.add
    i32.const 8
    global.get $MP
    i32.add
    i32.store
    block
    loop
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.const 5
    i32.lt_s
    i32.eqz
    br_if 1
    i32.const 52
    global.get $MP
    i32.add
    i32.load
    i32.const 12
    call $reserveStack
    call $setDynamicLink
    global.set $swap
    global.get $MP
    i32.const 4
    i32.add
    global.get $swap
    i32.store
    call $2
    call $freeStack
    call $print
    i32.const 52
    global.get $MP
    i32.add
    i32.const 52
    global.get $MP
    i32.add
    i32.load
    i32.const 1
    i32.const 8
    i32.mul
    i32.add
    i32.store
    i32.const 48
    global.get $MP
    i32.add
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.const 1
    i32.add
    i32.store
    br 0
    end
    end
    i32.const 48
    global.get $MP
    i32.add
    i32.const 0
    i32.store
    i32.const 52
    global.get $MP
    i32.add
    i32.const 52
    global.get $MP
    i32.add
    i32.load
    i32.const 1
    i32.const 8
    i32.mul
    i32.sub
    i32.store
    block
    loop
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.const 5
    i32.lt_s
    i32.eqz
    br_if 1
    i32.const 52
    global.get $MP
    i32.add
    i32.load
    i32.const 12
    call $reserveStack
    call $setDynamicLink
    global.set $swap
    global.get $MP
    i32.const 4
    i32.add
    global.get $swap
    i32.store
    call $2
    call $freeStack
    call $print
    i32.const 52
    global.get $MP
    i32.add
    i32.const 52
    global.get $MP
    i32.add
    i32.load
    i32.const 1
    i32.const 8
    i32.mul
    i32.sub
    i32.store
    i32.const 48
    global.get $MP
    i32.add
    i32.const 48
    global.get $MP
    i32.add
    i32.load
    i32.const 1
    i32.add
    i32.store
    br 0
    end
    end
    i32.const 0
)
)