package com.example.SecuritywithLeaners.Service;

import com.example.SecuritywithLeaners.DTO.ResponseDTO;
import com.example.SecuritywithLeaners.DTO.TrailPermitDTO;
import com.example.SecuritywithLeaners.Entity.TrialPermit;
import com.example.SecuritywithLeaners.Repo.StudentRepo;
import com.example.SecuritywithLeaners.Repo.TrialPermitRepo;
import com.example.SecuritywithLeaners.Util.varList;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class TrialPermitService {

    @Autowired
    TrialPermitRepo trialPermitRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseDTO SaveTrailPermit(TrailPermitDTO trialPermitDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (studentRepo.existsById(trialPermitDTO.getStdID())) {
            if(!(trialPermitRepo.existsById(trialPermitDTO.getSerialNo()))){
                try {
                TrialPermit data = modelMapper.map(trialPermitDTO, TrialPermit.class);
                trialPermitRepo.save(data);
                responseDTO.setMessage("Success");
                responseDTO.setContent("Saved");
                responseDTO.setStatus(HttpStatus.ACCEPTED);
                responseDTO.setCode(varList.RSP_SUCCES);
            } catch (Exception e) {
                log.error("Error while saving trial permit", e);
                responseDTO.setMessage("Failed");
                responseDTO.setContent("Failed");
                responseDTO.setStatus(HttpStatus.BAD_REQUEST);
                responseDTO.setCode(varList.RSP_FAIL);
            }}
            else {responseDTO.setCode(varList.RSP_DUPLICATED);
                responseDTO.setMessage("Duplicated");
                responseDTO.setContent("Duplicated");
                responseDTO.setStatus(HttpStatus.ALREADY_REPORTED);}

        }else{responseDTO.setMessage("Not no Exists");
            responseDTO.setContent("Not no Exists");
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
            responseDTO.setCode(varList.RSP_FAIL);}
        return responseDTO;
    }
}
