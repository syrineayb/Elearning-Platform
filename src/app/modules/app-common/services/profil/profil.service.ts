import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProfileResponse} from "../../../../models/profile/profile-response";

@Injectable({
  providedIn: 'root'
})
export class ProfilService {
  baseUrl = environment.app.baseUrl;

  constructor(private http: HttpClient) {}

  getLoggedInUserId(): Observable<number> {
    // Implement logic to fetch logged-in user's ID from backend
    // Make an HTTP GET request to the appropriate endpoint
    return this.http.get<number>(`${this.baseUrl}/api/loggedInUserId`);
  }

  getProfile(userId: number): Observable<ProfileResponse> {
    // Implement logic to fetch user profile data from backend
    // Make an HTTP GET request to the appropriate endpoint
    return this.http.get<ProfileResponse>(`${this.baseUrl}/api/${userId}`);
  }
}
