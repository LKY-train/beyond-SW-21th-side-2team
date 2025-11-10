package com.team2.project.controller;

// SubwayManager.java
import com.team2.project.model.dto.StationDTO;
import com.team2.project.view.StationByLinePrinter;

import java.util.ArrayList;

public class SubwayManager {
    private ArrayList<ArrayList<StationDTO>> stations;
    private StationByLinePrinter sp;

    public void setUp() {
        String sinbundang = "광교중앙-상현-성복-수지구청-동천-미금-정자-판교-청계산입구-양재시민의숲-양재-강남-신논현-논현-신사";
        String suinbundang = "기흥-구성-보정-죽전-오리-미금-정자-수내-서현-이매-야탑-모란-태평-가천대-복정-수서-대모산입구-개포동-구룡-도곡";
        String line3 = "수서-일원-대청-학여울-대치-도곡-매봉-양재-남부터미널-교대-고속터미널-잠원-신사";
    }


    public ArrayList<StationDTO> bfs(int start) {
        return new ArrayList<>();
    }
}

