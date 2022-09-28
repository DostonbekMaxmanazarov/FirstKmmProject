//
//  MoneyCourseView.swift
//  iosApp
//
//  Created by iGenius on 12/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class CurrencyViewState:ObservableObject{
    @Published var resultValue:Double = 0
}

extension CurrencyViewState : ICurrencyView{
    func sendCurrencyData(currencyDataResponse: CurrencyDataResponse) {
        resultValue = currencyDataResponse.result as! Double
    }
    
    func showError(error: KotlinThrowable) {
        
    }
    
    func showErrorMessage(message: String) {
        
    }
    
    func showLoading(show: Bool) {
        
    }

}

struct CurrencyView: View {
    
    @ObservedObject private var viewState:CurrencyViewState
    
    private let presenter:CurrencyPresenterImpl
    
    @State private var inputText = ""
    
    @State var fromSpinner = ""
    
    @State var toSpinner = ""
    
    var dropDownList = ["UZS","CAD","HKD","ISK","EUR",
                        "DKK","HUF","CZK","AUD","RON",
                        "SEK","IDR","INR","BRL","RUB",
                        "HRK","JPY","THB","CHF","SGD",
                        "PLN","BGN","CNY","NOK","NZD",
                        "ZAR","USD","MXN","ILS","GBP",
                        "KRW","MYR","PHP"]
    
    init(){
        let viewState = CurrencyViewState()
        self.viewState = viewState
        self.presenter = CurrencyPresenterImpl().attachView(view: viewState) as! CurrencyPresenterImpl
    }
    
    var body: some View {
        
        VStack{
        
            Text("Currency Convert")
                .font(.system(size: 20,design: .serif))
                .foregroundColor(Color.black)
                .fontWeight(.heavy)
                .italic()
                .padding()
                
            Spacer()
            
            HStack{
                
                TextField("Amount",text: $inputText)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .background(Color.gray.opacity(0.3).cornerRadius(10))
                    .foregroundColor(Color.black)
                    .font(.system(size: 12))
                    .padding()
                
                Text("From")
                    .font(.system(size:14))
                    .foregroundColor(Color.black.opacity(0.9))
                    .italic()
                
                Menu {
                    
                    ForEach(dropDownList, id: \.self){ client in
                        Button(client) {
                            self.fromSpinner = client
                            }
                        }
                      } label: {
                          
                          VStack(spacing: 5){
                              
                              HStack{
                                  ZStack{
                                      Rectangle()
                                          .stroke(Color.gray,lineWidth: 1)
                                          .cornerRadius(3)
                                          .frame(width: 36,height: 36)
                                          .foregroundColor(Color.white.opacity(0.3))
                                          .ignoresSafeArea()
                                          
                                      Text(fromSpinner)
                                          .font(.system(size:14))
                                          .foregroundColor(Color.black.opacity(0.7))
                                          .italic()
                                  }
                              }
                              .padding(.horizontal)
                          }
                      }
                
                Text("To")
                    .font(.system(size:14))
                    .foregroundColor(Color.black.opacity(0.9))
                    .italic()
                
                Menu {
                    
                    ForEach(dropDownList, id: \.self){ client in
                        Button(client) {
                            self.toSpinner=client
                            }
                        }
                      } label: {
                          
                          VStack(spacing: 5){
                              
                              HStack{
                                  
                                  ZStack{
                                      
                                      Rectangle()
                                          .stroke(Color.gray,lineWidth: 1)
                                          .cornerRadius(3)
                                          .frame(width: 36,height: 36)
                                          .foregroundColor(Color.white.opacity(0.3))
                                          .ignoresSafeArea()
                                          
                                      Text(toSpinner)
                                          .font(.system(size:14))
                                          .foregroundColor(Color.black.opacity(0.7))
                                          .italic()
                                  }
                              }
                              .padding(.horizontal)
                          }
                      }
            }
            
            Spacer()
            
            Text("\(viewState.resultValue)")
                .font(.system(size: 14,design: .serif))
                .foregroundColor(Color.black)
                .fontWeight(.heavy)
                .italic()
                .padding()
            
            Spacer()
            
            Button {
                
                presenter.loadCurrency(from: fromSpinner, to: toSpinner, amount: inputText)
                
            } label: {
                Text("Convert")
                    .padding(.vertical, 8)
                    .frame( maxWidth:.infinity)
                    .background(Color.blue.cornerRadius(15))
                    .foregroundColor(Color.white)
                    
                    
            }.padding()

        }
    }
}

struct MoneyCourseView_Previews: PreviewProvider {
    static var previews: some View {
        CurrencyView()
    }
}
