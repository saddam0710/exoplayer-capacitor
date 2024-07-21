import Foundation

@objc public class ExoPlayer: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
