package duyndph34554.fpoly.app_dat_com_tam.available

enum class RouterNameScreen(val router: String) {
    Home("Home"),
    Statistical("Statistical"),
    Manage("Manage"),
    Support("Support"),
    Splash("Splash"),
    Login("Login"),
    BottomScreen("Bottom"),
    ManageFood("ManageFood"),
    ManageTypeRice("ManageTypeRice"),
    AddTypeRice("AddTypeRice"),
    UpdateTypeRice("UpdateTypeRice"),

    AddFood("AddFood"),
    UpdateFood("UpdateFood/{foodId}");

    companion object {
        fun UpdateFoodWithId(foodId: Int) = "UpdateFood/$foodId"
    }


}