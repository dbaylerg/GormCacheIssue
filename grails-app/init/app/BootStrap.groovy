package app

class BootStrap {

    def init = { servletContext ->

        A.withNewTransaction {

            if (A.count() == 0) {
                new A().save()
                new B().save()
                new C().save()
            }
        }
    }
    def destroy = {
    }
}
