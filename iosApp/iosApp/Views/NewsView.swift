//
//  NewsView.swift
//  iosApp
//
//  Created by iGenius on 12/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class NewsViewState : ObservableObject{
    @Published var currentDescription:String=""
    
    @Published var newsData:[Article]?
}

extension NewsViewState:INewsView{
    
    func sendNewsData(newsDataResponse: NewsDataResponse) {
        newsData=newsDataResponse.articles as? [Article]
    }
    
    func showError(error: KotlinThrowable) {
        
    }
    
    func showErrorMessage(message: String) {
        
    }
    
    func showLoading(show: Bool) {
        
    }
    
    
}

struct NewsView: View {
    
    @ObservedObject private var viewState:NewsViewState
    
    private let presenter: NewsPresenterImpl
    
    init(){
        let viewState=NewsViewState()
        self.viewState=viewState
        self.presenter=NewsPresenterImpl().attachView(view: viewState) as! NewsPresenterImpl
        presenter.loadNews(country: "us", category: "business")
    }
    
    var body: some View {
        
        VStack{
            
            if viewState.newsData != nil {
                
                if #available(iOS 15.0, *) {
                    
                    List{
                        
                        ForEach(viewState.newsData!, id: \.self){ news in
                            
                            let sourse=news.sourceNews
                            
                            let sourseName = "\(String(describing: sourse?.name))"
                            
                            let title="\(String(describing: news.titleNews))"
                            
                            let description="\(String(describing: news.description))"
                            
                            VStack(spacing: 10){
                                
                                
                                VStack{
                                    
                                    Text("Author: ")
                                        .fontWeight(.bold)
                                        .italic()
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 18,design: .serif))
                                        .fontWeight(.heavy)
                                    
                                    Text(sourseName)
                                        .foregroundColor(Color.black)
                                        .italic()
                                        .font(.system(size: 18,design: .serif))
                                    
                                 
                                }
                                
                                VStack{
                                    
                                    Text("Title: ")
                                        .fontWeight(.bold)
                                        .italic()
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 18,design: .serif))
                                        .fontWeight(.heavy)
                                    
                                    Text(title)
                                        .foregroundColor(Color.black)
                                        .italic()
                                        .font(.system(size: 18,design: .serif))
                                    
                                }
                                
                                VStack{
                                    
                                    Text("Description: ")
                                        .fontWeight(.bold)
                                        .italic()
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 18,design: .serif))
                                        .fontWeight(.heavy)
                                    
                                    Text(description)
                                        .foregroundColor(Color.black)
                                        .italic()
                                        .font(.system(size: 18,design: .serif))
                                    
                                }
                                
                                Spacer()
                            }
                            
                        }
                    }.refreshable {
                        presenter.loadNews(country: "us", category: "business")
                    }
                    
                } else {
                    
                }
            }
        }
    }
}

struct NewsView_Previews: PreviewProvider {
    static var previews: some View {
        NewsView()
    }
}
