package `in`.gov.edudel.smcapp.models

enum class LoginType(val displayValue: String = "Public User", val accessLevel: AccessLevel=AccessLevel.Public) {
    Teacher("Teacher", AccessLevel.Member),
    HOS("HOS", AccessLevel.School),
    Member("Parent", AccessLevel.Member),
    SocialWorker("Social Worker", AccessLevel.Member),
    Zone("DDE Zone", AccessLevel.Zone),
    District("DDE District", AccessLevel.District),
    Hq("Director/ Minister", AccessLevel.Hq),
    Admin ("Admin", AccessLevel.Admin)
}