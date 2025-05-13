// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorSecondDisplay",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorSecondDisplay",
            targets: ["SecondDisplayPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "SecondDisplayPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/SecondDisplayPlugin"),
        .testTarget(
            name: "SecondDisplayPluginTests",
            dependencies: ["SecondDisplayPlugin"],
            path: "ios/Tests/SecondDisplayPluginTests")
    ]
)