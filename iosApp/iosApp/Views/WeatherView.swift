//
//  WeatherView.swift
//  iosApp
//
//  Created by iGenius on 12/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class WeatherViewState:ObservableObject{
    @Published var currentTemp:Double=0
    @Published var currentDescription:String=""
}

extension WeatherViewState:IWeatherMainView{
    func sendWeatherData(weatherDataResponse: WeatherDataResponse) {
        currentTemp=weatherDataResponse.main?.temp as! Double
        currentDescription=weatherDataResponse.weather![0].self.descriptionTemp!
    }
    
    func showError(error: KotlinThrowable) {
    
    }
    
    func showErrorMessage(message: String) {
 
    }
    
    func showLoading(show: Bool) {
   
    }
    
    
}



struct WeatherView: View {
    
     @State var valueCountryName = ""
    
    @State var value = ""
    
        var placeholder = "Select country"
    
        var dropDownList = ["Uzbekistan", "Tashkent",
                            "Jizzakh", "Navoiy",
                            "Samarkand","Bukhara",
                            "Qarshi","Shahrisabz",
                            "Namangan","Andijan","Kokand"]
    
    @ObservedObject private var viewState:WeatherViewState
    
    private let presenter: WeatherMainPresenterImpl
    
    init(){
        let viewState=WeatherViewState()
        self.viewState=viewState
        self.presenter=WeatherMainPresenterImpl().attachView(view: viewState) as! WeatherMainPresenterImpl
    }
    
    var body: some View {
        
         ZStack{
            
            VStack{
                
                ZStack{
                    
                    Image("background_foto")
                        .resizable()
                        .ignoresSafeArea()
                    
                    VStack{
                        
                        Menu {
                            
                            ForEach(dropDownList, id: \.self){ client in
                                Button(client) {
                                        self.value = client
                                    presenter.loadWeather(countryName: self.value)
                                    }
                                }
                              } label: {
                                  
                                  VStack(spacing: 5){
                                      
                                      HStack{
                                          
                                          Text(value.isEmpty ? placeholder : value)
                                              .foregroundColor(value.isEmpty ? .gray : .white)
                                              .italic()
                                          
                                          Spacer()
                                          
                                          Image(systemName: "chevron.down")
                                              .foregroundColor(Color.white)
                                              .font(Font.system(size: 13, weight: .bold))
                                      }
                                      .padding(.horizontal)
                                      
                                      Rectangle()
                                          .fill(Color.white)
                                          .frame(height: 2)
                                          .padding(.horizontal,5)
                                  }
                              }
                        
                        Text(self.value.isEmpty ? "" : "\(Int(viewState.currentTemp-273.15))")
                            .fontWeight(.bold)
                            .font(.system(size: 48, weight: .light, design: .serif))
                            .padding(.top,10)
                            .foregroundColor(Color.white)
                            .font(.title)
            
                        Text(viewState.currentDescription)
                            .fontWeight(.bold)
                            .padding(.top,1)
                            .foregroundColor(Color.white)
                        
                        Spacer()
            
                    }
                }
            
                   VStack{
                       
                       HStack{
                        
                           Text("Maximum temp")
                               .padding()
                           
                           Spacer()
                           
                           Text("25")
                               .padding()
                       }
                       
                       Rectangle()
                           .fill(Color.black)
                           .frame(height:1)
                       
                       HStack{
                        
                           Text("Maximum temp")
                               .padding()
                               
                           
                           Spacer()
                           
                           Text("25")
                               .padding()
                       }
                   }
            }
        } 
    }
}

struct WeatherView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherView()
    }
}
