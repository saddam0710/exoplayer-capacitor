// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "ExoplayerCapacitor",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "ExoplayerCapacitor",
            targets: ["ExoPlayerPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "ExoPlayerPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ExoPlayerPlugin"),
        .testTarget(
            name: "ExoPlayerPluginTests",
            dependencies: ["ExoPlayerPlugin"],
            path: "ios/Tests/ExoPlayerPluginTests")
    ]
)