package com.hotdealnoti.common.paging

class PageRequest(
    page: Int,
    contentSize: Int,
    sort: String = "mock,desc"
) : HashMap<String, String>() {

    init {
        put("page",page.toString())
        put("size",contentSize.toString())
        put("sort",sort)
    }

}
