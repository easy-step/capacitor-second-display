import Foundation

@objc public class SecondDisplay: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
