package com.vlad.savemagmet.controller;

import com.vlad.savemagmet.entity.Magnet;
import com.vlad.savemagmet.entity.Message;
import com.vlad.savemagmet.service.MagnetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/magnet")
public class RestAPIController {

    @Autowired
    MagnetServices magnetServices;

    @PostMapping("/create")
    public ResponseEntity<Message> addNewMagnet(@RequestBody Magnet magnet) {
        try {

            magnetServices.saveMagnets(magnet);

            return new ResponseEntity<Message>(new Message("Upload Successfully!",
                    Arrays.asList(magnet), ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Fail to post a new magnet!",
                    null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieveinfos")
    public ResponseEntity<Message> retrieveMagnetInfo() {

        try {
            List<Magnet> magnetInfos = magnetServices.getMagnetInfos();

            return new ResponseEntity<Message>(new Message("Get magnet' Infos!",
                    magnetInfos, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Fail!",
                    null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findone/{id}")
    public ResponseEntity<Message> getMagnetById(@PathVariable long id) {
        try {
            Optional<Magnet> optMagnet = magnetServices.getMagnetById(id);

            if (optMagnet.isPresent()) {
                return new ResponseEntity<Message>(new Message("Successfully! Retrieve a magnet by id = " + id,
                        Arrays.asList(optMagnet.get()), ""), HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(new Message("Failure -> NOT Found a magnet by id = " + id,
                        null, ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure",
                    null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Message> updateMagnetById(@RequestBody Magnet _magnet,
                                                    @PathVariable long id) {
        try {
            if (magnetServices.checkExistedMagnet(id)) {
                Magnet magnet = magnetServices.getMagnetById(id).get();

                //set new values for magnet
                magnet.setTitle(_magnet.getTitle());

                // save the change to database
                magnetServices.updateMagnet(magnet);

                return new ResponseEntity<Message>(new Message("Successfully! Updated a magnet "
                        + "with id = " + id,
                        Arrays.asList(magnet), ""), HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a magnet "
                        + "with id = " + id,
                        null, ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure",
                    null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Message> deleteMagnetById(@PathVariable long id) {
        try {
            // checking the existed of a magnet with id
            if (magnetServices.checkExistedMagnet(id)) {
                magnetServices.deleteMagnetById(id);
                return new ResponseEntity<Message>(new Message("Successfully! Delete a magnet with id = " + id,
                        null, ""), HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(new Message("Fail! Can NOT Found a magnet "
                        + "with id = " + id, null, ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure",
                    null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllMagnets")
    public ResponseEntity<Message> deleteAllMagnets() {
        try {
            magnetServices.deleteAlMagnets();
            return new ResponseEntity<Message>(new Message("Successfully! Delete all magnets",
                    null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure",
                    null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}