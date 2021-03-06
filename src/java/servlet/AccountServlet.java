/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package servlet;

import static java.lang.System.out;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Provides an Account Balance and Basic Withdrawal/Deposit Operations
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        double deposit = (double) session.getAttribute("deposit");
        double withdraw = (double) session.getAttribute("withdraw");
        double balance = (double) session.getAttribute("balance");
        request.getServletContext().getSessionCookieConfig().setHttpOnly(true);
        
        
        if (deposit > 0) {
            deposit(deposit);
            out.println(getBalance());
        }
        
        if (withdraw <= balance){
            withdraw(withdraw);
            out.println(getBalance());
        } 

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
    
}
