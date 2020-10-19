package br.edu.mariaregina.projeto_maria.api;

import br.edu.mariaregina.projeto_maria.model.Elefante;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("{elefante}")
    Call<Elefante> getDados(@Path("elefante") String name);
}
