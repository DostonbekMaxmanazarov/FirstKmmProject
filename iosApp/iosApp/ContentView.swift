import SwiftUI

struct ContentView: View {
    
	var body: some View {
        
        TabView{
    
            WeatherView()
                .tabItem(){
                    Image(systemName: "cloud.sun")
                        .foregroundColor(Color.black)
                    Text("Weather")
                }
            
            NewsView()
                .tabItem(){
                    Image(systemName: "newspaper")
                    Text("News")
                }
            
            CurrencyView()
                .tabItem(){
                    Image(systemName: "sun.max")
                    Text("Course")
                }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
