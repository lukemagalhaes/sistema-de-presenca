import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aluno } from './aluno.model';

@Injectable({
  providedIn: 'root'
})

export class AlunoService {
  private apiUrl = 'https://sistema-de-presenca.onrender.com/api/alunos'; 

  constructor(private http: HttpClient) { }

  getAllAlunos(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.apiUrl);
  }

  getAlunoById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}

